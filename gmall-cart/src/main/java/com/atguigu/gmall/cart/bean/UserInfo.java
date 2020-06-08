package com.atguigu.gmall.cart.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZCC
 * @date 2020/6/8 21:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long userId;
    private String userKey;
}