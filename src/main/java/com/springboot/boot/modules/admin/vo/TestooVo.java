package com.springboot.boot.modules.admin.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data

public class TestooVo extends BaseRowModel {



    @ExcelProperty(value = "name", index = 0)
    private String name;


}