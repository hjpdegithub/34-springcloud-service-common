package com.springboot.boot.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
 
/**
 * 导入导出数据转换
 */
public class TestPassConverter implements Converter<Integer> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }
 
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }
 
    /**
     * 导入枚举类型转换
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if("通过".equals(cellData.getStringValue())){
            return  1;
        }else if("未通过".equals(cellData.getStringValue())){
            return  2;
        }
        return 0;
    }

    /**
     * 导出枚举类型转换
     * @param pass
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(Integer pass, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (pass == 1) {
            return new CellData("通过");
        } else if (pass == 2) {
            return new CellData("未通过");
        }
        return new CellData("");
    }
}