package com.atguigu.gmall.pms.api;

import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface GmallPmsApi {

    /**
     * 分页查询spu
     * @param paramVo
     * @return
     */
    @PostMapping("pms/spu/page")
    public ResponseVo<List<SpuEntity>> querySpuPage(@RequestBody PageParamVo paramVo);

    /**
     * 根据spuId查询sku
     * @param spuId
     * @return
     */
    @GetMapping("pms/sku/spu/{spuId}")
    public ResponseVo<List<SkuEntity>> List(@PathVariable("spuId") Long spuId);

    /**
     * 根据品牌id查询品牌
     * @param id
     * @return
     */
    @GetMapping("pms/brand/{id}")
    public ResponseVo<BrandEntity> queryBrandById(@PathVariable("id") Long id);


    /**
     * 根据分类id查询分类信息
     * @param id
     * @return
     */
    @GetMapping("pms/category/{id}")
    public ResponseVo<CategoryEntity> queryCategoryById(@PathVariable("id") Long id);

    /**
     * 根据skuId查询销售搜索类型的规格参数以及值
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/{skuId}")
    public ResponseVo<List<SkuAttrValueEntity>> querySkuAttrValuesBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * 根据spuId查询基本搜索类型的规格参数以及值
     * @param spuId
     * @return
     */
    @GetMapping("pms/spuattrvalue/spu/{spuId}")
    public ResponseVo<List<SpuAttrValueEntity>> querySpuAttrValuesBySpuId(@PathVariable("spuId")Long spuId);


    /**
     * 根据spuId查询spu
     * @param id
     * @return
     */
    @GetMapping("pms/spu/{id}")
    public ResponseVo<SpuEntity> querySpuById(@PathVariable("id") Long id);

    /**
     * 根据父id查询子分类
     * @param parentId
     * @return
     */
    @GetMapping("pms/category/parent/{parentId}")
    public ResponseVo<List<CategoryEntity>> queryCategory(@PathVariable("parentId") Long parentId);

    @GetMapping("pms/category/parent/with/subs/{parentId}")
    public ResponseVo<List<CategoryEntity>> queryCategoriesWithSubByPid(@PathVariable("parentId")Long parentId);

    /**
     * 根据skuId查询sku信息
     * @param id
     * @return
     */
    @GetMapping("pms/sku/{id}")
    public ResponseVo<SkuEntity> querySkuById(@PathVariable("id") Long id);

    /**
     * 根据三级分类的id查询出一二三级分类集合
     * @param cid3
     * @return
     */
    @GetMapping("pms/category/all/{cid3}")
    public ResponseVo<List<CategoryEntity>> queryCategoriesByCid3(@PathVariable("cid3")Long cid3);

    /**
     * 根据skuId查询sku的图片列表（skuImages）
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuimages/sku/{skuId}")
    public ResponseVo<List<SkuImagesEntity>>  queryImagesBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * 根据spuId查询spu下所有sku的销售属性
     * @param spuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/spu/{spuId}")
    public ResponseVo<List<SaleAttrValueVo>> querySaleAttrValuesBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据skuId查询当前sku的销售属性
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/sale/{skuId}")
    public ResponseVo<List<SkuAttrValueEntity>> querySaleAttrValuesBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * 根据spuId查询销售属性和skuId的对应关系
     * @param spuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/spu/{spuId}")
    public ResponseVo<String> querySkuJsonsBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据spuId查询spu的描述信息
     * @param spuId
     * @return
     */
    @GetMapping("pms/spudesc/{spuId}")
    public ResponseVo<SpuDescEntity> querySpuDescById(@PathVariable("spuId") Long spuId);

    /**
     * 根据分类id、spuId和skuId查询分组及组下的规格参数和值
     * @param cid
     * @param spuId
     * @param skuId
     * @return
     */
    @GetMapping("pms/attrgroup/item/group")
    public ResponseVo<List<ItemGroupVo>> queryItemGroupVoByCidAndSpuIdAndSkuId(
            @RequestParam("cid")Long cid,
            @RequestParam("spuId")Long spuId,
            @RequestParam("skuId")Long skuId
    );

}
