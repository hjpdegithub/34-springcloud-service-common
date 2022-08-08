package com.springboot.boot.modules.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MyStudyVo {

    @ApiModelProperty("课程id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("课程名称")
    private String currName;
    @ApiModelProperty("学习时限")
    private Date studyTime;
    @ApiModelProperty("课程格式1文本2视频")
    private Integer formate;
    @ApiModelProperty("是否定制")
    private Integer type;

}
