package com.atguigu.gmall.index.service;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.index.config.GmallCache;
import com.atguigu.gmall.index.feign.GmallPmsClient;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class IndexService {

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private static final String KEY_PREFIX = "index:cats:";

    public List<CategoryEntity> queryLvl1Categories() {
        ResponseVo<List<CategoryEntity>> listResponseVo = this.pmsClient.queryCategory(0l);
        return listResponseVo.getData();
    }

    @GmallCache(prefix = KEY_PREFIX, lock = "lock", timeout = 129600l, random = 10080)
    public List<CategoryEntity> queryLvl2CategoriesWithSubs(Long pid) {



        // 2.没有，则查询数据库
        ResponseVo<List<CategoryEntity>> listResponseVo = this.pmsClient.queryCategoriesWithSubByPid(pid);
        List<CategoryEntity> categoryEntities = listResponseVo.getData();


        return categoryEntities;
    }


    public List<CategoryEntity> queryLvl2CategoriesWithSubs2(Long pid) {
        // 1.查询缓存 有则直接命中返回
        String json = this.redisTemplate.opsForValue().get(KEY_PREFIX + pid);
        if (StringUtils.isNotBlank(json)){
            return JSON.parseArray(json, CategoryEntity.class);
        }

        // 为了防止缓存击穿，在这里添加分布式锁
        RLock fairLock = this.redissonClient.getFairLock("lock" + pid);
        fairLock.lock();

        // 再次查询缓存：1.加锁的过程中可能有其他请求放入缓存
        // 2.高并发情况下，第一个请求获取到锁之后，发送远程请求查询数据并放入缓存。其他请求就不用发送请求
        String json2 = this.redisTemplate.opsForValue().get(KEY_PREFIX + pid);
        if (StringUtils.isNotBlank(json2)){
            return JSON.parseArray(json2, CategoryEntity.class);
        }

        // 2.没有，则查询数据库
        ResponseVo<List<CategoryEntity>> listResponseVo = this.pmsClient.queryCategoriesWithSubByPid(pid);
        List<CategoryEntity> categoryEntities = listResponseVo.getData();
        // 放入缓存
        this.redisTemplate.opsForValue().set(KEY_PREFIX + pid, JSON.toJSONString(categoryEntities),3*30+new Random().nextInt(7), TimeUnit.DAYS);

        fairLock.unlock();

        return categoryEntities;
    }
}
