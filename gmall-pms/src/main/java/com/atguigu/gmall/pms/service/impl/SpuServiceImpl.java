package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.mapper.SkuMapper;
import com.atguigu.gmall.pms.mapper.SpuDescMapper;
import com.atguigu.gmall.pms.service.*;
import com.atguigu.gmall.pms.vo.SkuVo;
import com.atguigu.gmall.pms.vo.SpuAttrValueVo;
import com.atguigu.gmall.pms.vo.SpuVo;
import com.atguigui.gmall.sms.vo.SkuSaleVo;
import com.atguigui.gmall.sms.vo.api.GmallSmsApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SpuMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo querySpuInfo(Long categoryId, PageParamVo pageParamVo) {
        QueryWrapper<SpuEntity> queryWrapper = new QueryWrapper<>();
        // 如果分类id不为0，要根据分类id查，否则查全部
        if (categoryId != 0){
            queryWrapper.eq("category_id",categoryId);
        }

        // 如果用户输入了检索条件，根据检索条件查
        String key = pageParamVo.getKey();
        if (StringUtils.isNoneBlank(key)){
            queryWrapper.and(t -> t.like("id",key).or().like("name",key));
        }
        IPage<SpuEntity> page = this.page(pageParamVo.getPage(), queryWrapper);
        return new PageResultVo(page);
    }

    @Autowired
    private SpuDescMapper descMapper;

    @Autowired
    SpuAttrValueService spuAttrValueService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuAttrValueService skuAttrValueService;

    @Autowired
    private GmallSmsClient gmallSmsApi;

    @Autowired
    private SpuDescService descService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @GlobalTransactional
    public void bigSave(SpuVo spuVo) throws FileNotFoundException {
        // 1. 保存spu相关信息
        // 1.1. 保存spu的信息 pms_spu
        Long spuVoId = saveSpu(spuVo);


//            new FileInputStream("xxxx");

//        int x = 1 / 0;

        // 1.2. 保存spu的描述信息 pms_spu_desc
        this.descService.saveSpuDesc(spuVo, spuVoId);
        // 1.3. 保存spu的基本属性信息 pms_spu_attr_value
        saveBaseAttr(spuVo, spuVoId);

        // 2. 保存sku相关信息
        saveSku(spuVo, spuVoId);

//                int x = 1 / 0;
        this.rabbitTemplate.convertAndSend("GMALL_ITEM_EXCHANGE", "item.insert", spuVoId);

    }

    private void saveSku(SpuVo spuVo, Long spuVoId) {
        List<SkuVo> skuVos = spuVo.getSkus();
        if (CollectionUtils.isEmpty(skuVos)){
            return;
        }
        skuVos.forEach(skuVo -> {
            // 2.1. 保存sku的信息 pms_sku
            SkuEntity skuEntity = new SkuEntity();
            BeanUtils.copyProperties(skuVo,skuEntity);
            // 品牌和分类的id需要从spuInfo中获取
            skuEntity.setBrandId(spuVo.getBrandId());
            skuEntity.setCatagoryId(spuVo.getCategoryId());
            // 获取图片列表
            List<String> images = skuVo.getImages();
            // 如果图片列表不为null，则设置默认图片
            if (!CollectionUtils.isEmpty(images)){
                // 设置第一张图片作为默认图片
                skuEntity.setDefaultImage(StringUtils.isNoneBlank(skuEntity.getDefaultImage()) ? skuEntity.getDefaultImage() : images.get(0));
            }
            skuEntity.setSpuId(spuVoId);
            this.skuMapper.insert(skuEntity);
            //获取skuId
            Long skuId = skuEntity.getId();

            // 2.2. 保存sku的图片信息 pms_sku_images
            if (!CollectionUtils.isEmpty(images)){
                List<SkuImagesEntity> skuImagesEntities = images.stream().map(image -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setDefaultStatus(StringUtils.equals(images.get(0),image) ? 1 : 0);
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setSort(0);
                    skuImagesEntity.setUrl(image);
                    return skuImagesEntity;
                }).collect(Collectors.toList());
                this.skuImagesService.saveBatch(skuImagesEntities);
            }


            // 2.3. 保存sku的销售属性 pms_sku_attr_value
            List<SkuAttrValueEntity> saleAttrs = skuVo.getSaleAttrs();
            if (!CollectionUtils.isEmpty(saleAttrs)){
                saleAttrs.forEach(skuAttr -> {
                    skuAttr.setSort(0);
                    skuAttr.setSkuId(skuId);
                });
                this.skuAttrValueService.saveBatch(saleAttrs);
            }


            // 3. 保存sku的营销信息
            SkuSaleVo skuSaleVo = new SkuSaleVo();
            skuSaleVo.setSkuId(skuId);
            BeanUtils.copyProperties(skuVo,skuSaleVo);
            this.gmallSmsApi.saveSkuSaleInfo(skuSaleVo);
            // 3.1. 保存积分优惠 sms_sku_bounds

            // 3.2. 保存满减信息 sms_sku_full_reduction

            // 3.3. 保存打折信息 sms_sku_ladder
        });
    }

    private void saveBaseAttr(SpuVo spuVo, Long spuVoId) {
        List<SpuAttrValueVo> baseAttrs = spuVo.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)){
            List<SpuAttrValueEntity> spuAttrValueEntities = baseAttrs.stream().map(attr ->{
                SpuAttrValueEntity spuAttrValueEntity = new SpuAttrValueEntity();
                BeanUtils.copyProperties(attr,spuAttrValueEntity);
                spuAttrValueEntity.setSpuId(spuVoId);
                return spuAttrValueEntity;
            }).collect(Collectors.toList());
            this.spuAttrValueService.saveBatch(spuAttrValueEntities);

        }
    }



    private Long saveSpu(SpuVo spuVo) {
        spuVo.setCreateTime(new Date());
        spuVo.setUpdateTime(spuVo.getCreateTime());
        this.save(spuVo);
        return spuVo.getId();
    }

}