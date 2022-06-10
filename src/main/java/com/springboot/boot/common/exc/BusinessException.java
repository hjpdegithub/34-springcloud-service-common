package com.springboot.boot.common.exc;

import javax.servlet.http.HttpServletResponse;

/**
 * 业务异常类
 * User: lan-jian
 * Date: 2019-07-10
 * To change this template use File | Settings | File Templates.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer statusCode;

    public BusinessException() {
        super ();
        this.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public BusinessException(String message) {
        super (message);
        this.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }


    public BusinessException(Throwable cause) {
        super (cause);
        this.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public BusinessException(String message, Throwable cause) {
        super (message, cause);
        this.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public BusinessException(Integer statusCode, String message) {
        super (message);
        this.statusCode = statusCode;
    }

    public BusinessException(Integer statusCode, Throwable cause) {
        super (cause);
        this.statusCode = statusCode;
    }

    public BusinessException(Integer statusCode, String message, Throwable cause) {
        super (message, cause);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
