package com.springboot.boot.modules.admin.vo.test;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.utils.TestPassConverter;
import lombok.Data;

import java.util.Date;
@Data
public class MpExamAchievementMultiVo {
    @ExcelIgnore
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ExcelIgnore
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examId;
    //考试人
    @ExcelIgnore
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;
    //考试名称
    @ExcelProperty(value ="考试名称",index = 1)
    private String testName;
    //考试开始时间
    @ExcelProperty(value ="考试开始时间",index = 4)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //考试用时（分钟）
    @ExcelProperty(value ="考试用时（分钟）",index = 5)
    private Integer examTime;
    //考试成绩
    @ExcelProperty(value ="考试得分",index = 6)
    private Integer examAchievement;
    //是否通过考试
    @ExcelProperty(value = "是否通过考试",index = 7,converter = TestPassConverter.class)
    private Integer ifWhether;
    @ExcelProperty(value ="考试人",index = 0)
    private  String  testUserName;

    @ExcelProperty(value ="单位",index = 2)
    private  String unitName;
    @ExcelProperty(value ="部门",index = 3)
    private  String departmentName;

}