package com.springboot.boot.modules.admin.vo.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class TestCountVo {

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    //题目数量统计
    private Long titleCount;
    //试卷数量统计
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long testPaperCount;
    //考试次数统计
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long testTimesCount;
    //考试人数统计
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long testUsersCount;




}









