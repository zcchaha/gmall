package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku销售属性&值
 * 
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-05-17 11:28:58
 */
@Mapper
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValueEntity> {

    List<SkuAttrValueEntity> querySkuAttrValuesBySkuId(Long skuId);
}
