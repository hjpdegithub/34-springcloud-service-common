package com.springboot.boot.common;

import cn.hutool.core.util.StrUtil;
import com.springboot.boot.common.exc.ParamsException;

/**
 * @author lizh
 * @date 2021/12/11
 */
public class BaseValidator {

    protected void validBlank(String param,String errorMessage){
        if (StrUtil.isBlank(param)){
            throw new ParamsException(errorMessage);
        }
    }
    protected void validBlank(Long param,String errorMessage){
        if (param == null){
            throw new ParamsException(errorMessage);
        }
    }
}
