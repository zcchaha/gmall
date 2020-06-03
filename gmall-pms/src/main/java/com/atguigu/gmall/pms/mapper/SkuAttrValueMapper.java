package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<SkuAttrValueEntity> querySaleAttrValuesBySpuId(Long spuId);

    List<Map<String, Object>> querySkuJsonsBySpuId(Long spuId);

    List<SkuAttrValueEntity> querySkuAttrValuesBySkuIdAndGid(@Param("skuId") Long skuId, @Param("groupId") Long id);
}
