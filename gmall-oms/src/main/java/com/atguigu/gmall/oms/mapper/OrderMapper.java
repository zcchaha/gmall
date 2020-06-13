package com.atguigu.gmall.oms.mapper;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-06-12 17:15:13
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

    int closeOrder(String orderToken);
}
