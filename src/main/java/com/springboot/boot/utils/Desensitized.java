package com.springboot.boot.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.lang.annotation.*;

/**
 * 数据脱敏转换类
 */

@Target({ElementType.FIELD})//只能写在类上
@Inherited//可以被子类继承
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitized  {
    // 脱敏类型
    SensitiveTypeEnum typ();


}