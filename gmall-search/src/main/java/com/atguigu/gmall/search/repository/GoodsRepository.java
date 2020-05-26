package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.bean.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ZCC
 * @date 2020/5/26 18:48
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
