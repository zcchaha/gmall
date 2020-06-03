package com.atguigu.gmall.item.feign;

import com.atguigui.gmall.sms.vo.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
