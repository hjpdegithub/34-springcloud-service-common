package com.springboot.boot.common.exc;

/**
 *  统一处理异常类
 * @author lizh
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = -2360348418477814930L;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
