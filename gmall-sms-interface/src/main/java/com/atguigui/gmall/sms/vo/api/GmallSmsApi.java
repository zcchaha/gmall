package com.atguigui.gmall.sms.vo.api;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigui.gmall.sms.vo.SkuSaleVo;
import com.atguigui.gmall.sms.vo.vo.ItemSaleVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GmallSmsApi {
    @PostMapping("sms/skubounds/skusale/save")
    public ResponseVo<Object> saveSkuSaleInfo(@RequestBody SkuSaleVo skuSaleVo);

    /**
     * 根据skuId查询营销信息（sms）
     * @param skuId
     * @return
     */
    @GetMapping("sms/skubounds/sku/{skuId}")
    public ResponseVo<List<ItemSaleVo>> querySaleVosBySkuId(@PathVariable("skuId")Long skuId);
}
