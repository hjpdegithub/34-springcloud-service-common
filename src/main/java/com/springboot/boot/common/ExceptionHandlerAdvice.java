package com.springboot.boot.common;

import com.springboot.boot.common.exc.BaseException;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.common.exc.ParamsException;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller异常处理
 *
 * @author lizh
 * @date 2021/12/11
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handleException(Exception e) {
        log.error("", e);
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ApiResult handleBaseException(Exception e) {
        log.error("", e);
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler({ParamsException.class})
    public ApiResult paramsErrorHandler(ParamsException e) {
        log.error("参数校验错误", e);
        return ApiResult.error(400, e.getMessage());
    }

    /**
     * 自定义业务异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResult businessErrorHandler(BusinessException e) {
        log.error (e.getMessage (), e);
        return ApiResult.error(500, e.getMessage());
    }
}
