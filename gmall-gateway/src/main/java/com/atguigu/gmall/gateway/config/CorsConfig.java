package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author ZCC
 * @date 2020/5/18 10:06
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration config = new CorsConfiguration();
        //允许跨域域名，*：代表所有域名跨域访问
        config.addAllowedOrigin("http://manager.gmall.com");
        config.addAllowedOrigin("http://localhost:1000");
        //允许携带cookie信息
        config.setAllowCredentials(true);
        //允许所有类型的请求方法跨域访问
        config.addAllowedMethod("*");
        //允许携带所有的头信息跨域访问
        config.addAllowedHeader("*");


        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",config);



        return new CorsWebFilter(configurationSource);
    }
}
