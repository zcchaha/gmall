package com.atguigu.gmall.ums.mapper;

import com.atguigu.gmall.ums.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-06-05 19:57:16
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
