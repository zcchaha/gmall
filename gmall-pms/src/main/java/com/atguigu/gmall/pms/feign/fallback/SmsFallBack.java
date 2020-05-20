package com.atguigu.gmall.pms.feign.fallback;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigui.gmall.sms.vo.SkuSaleVo;
import org.springframework.stereotype.Component;

/**
 * @author ZCC
 * @date 2020/5/20 20:30
 */
@Component
public class SmsFallBack implements GmallSmsClient {

    public ResponseVo<Object> saveSkuSaleInfo(SkuSaleVo skuSaleVo) {
        return ResponseVo.fail("保存营销信息失败");
    }
}
