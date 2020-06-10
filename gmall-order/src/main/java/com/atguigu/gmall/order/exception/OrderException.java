package com.atguigu.gmall.order.exception;

/**
 * @author ZCC
 * @date 2020/6/10 23:06
 */
public class OrderException extends RuntimeException {
    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }
}
