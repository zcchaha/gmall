package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.pms.feign.fallback.SmsFallBack;
import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sms-service",fallback = SmsFallBack.class)
public interface GmallSmsClient extends GmallSmsApi {

}
