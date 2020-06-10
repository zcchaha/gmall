package com.atguigu.gmall.sms.service;

import com.atguigu.gmall.sms.SkuSaleVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.sms.entity.SkuBoundsEntity;

import java.util.List;

/**
 * 商品spu积分设置
 *
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-05-17 16:02:22
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    void saveSkuSaleInfo(SkuSaleVo skuSaleVo);

    List<ItemSaleVo> querySaleVosBySkuId(Long skuId);
}

