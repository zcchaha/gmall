package com.atguigu.gmall.ums.config;

/**
 * @author ZCC
 * @date 2020/6/5 20:31
 */
public class UserException extends RuntimeException {
    public UserException() {}

    public UserException(String message) {
        super(message);
    }
}
