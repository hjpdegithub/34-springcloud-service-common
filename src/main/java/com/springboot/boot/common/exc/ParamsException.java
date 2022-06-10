package com.springboot.boot.common.exc;

/**
 * 参数校验异常
 * @author lizh
 */
public class ParamsException extends BaseException {
    private static final long serialVersionUID = -6743320941246441793L;

    public ParamsException(String message) {
        super(message);
    }
}
