package com.atguigu.gmall.sms.mapper;

import com.atguigu.gmall.sms.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-05-17 16:02:22
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponEntity> {
	
}
