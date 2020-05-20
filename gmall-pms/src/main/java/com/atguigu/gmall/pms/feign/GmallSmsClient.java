package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.feign.fallback.SmsFallBack;
import com.atguigui.gmall.sms.vo.SkuSaleVo;
import com.atguigui.gmall.sms.vo.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sms-service",fallback = SmsFallBack.class)
public interface GmallSmsClient extends GmallSmsApi {

}
