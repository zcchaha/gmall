package com.atguigu.gmall.item.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZCC
 * @date 2020/6/5 18:09
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(
            @Value("${threadPool.coreSize}") Integer coreSize,
            @Value("${threadPool.maxSize}") Integer maxSize,
            @Value("${threadPool.timeout}") Integer timeout,
            @Value("${threadPool.blockingSize}") Integer blockingSize
    ){
        return new ThreadPoolExecutor(coreSize,maxSize, timeout, TimeUnit.SECONDS,new ArrayBlockingQueue<>(blockingSize));
    }
}
