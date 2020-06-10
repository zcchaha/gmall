package com.atguigu.gmall.pms.feign.fallback;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.sms.SkuSaleVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZCC
 * @date 2020/5/20 20:30
 */
@Component
public class SmsFallBack implements GmallSmsClient {

    public ResponseVo<Object> saveSkuSaleInfo(SkuSaleVo skuSaleVo) {
        return ResponseVo.fail("保存营销信息失败");
    }

    @Override
    public ResponseVo<List<ItemSaleVo>> querySaleVosBySkuId(Long skuId) {
        return ResponseVo.fail("查询营销信息失败");
    }
}
