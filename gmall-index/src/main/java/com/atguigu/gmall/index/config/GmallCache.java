package com.atguigu.gmall.index.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {

    /**
     * 缓存前缀
     * @return
     */
    String prefix() default "";

    /**
     * 防止缓存的击穿：分布式锁的名称
     * @return
     */
    String lock() default "lcok";

    /**
     * 缓存时间，单位：分钟
     * @return
     */
    long timeout() default 60l;

    /**
     * 防止缓存雪崩，添加过期时间的随机值
     * @return
     */
    int random() default 0;
}
