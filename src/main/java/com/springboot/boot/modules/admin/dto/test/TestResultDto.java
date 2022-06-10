package com.springboot.boot.modules.admin.dto.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TestResultDto {
    @ApiModelProperty("考试成绩表id")
    private Long id;
    @ApiModelProperty("试卷id")
    private Long examId;
    @ApiModelProperty("用户id考试人ID")
    private Long userId;
    @ApiModelProperty("考试类型1模拟考试2在线考试")
    private Integer type;
    @ApiModelProperty("考试用时")
    private Integer examTime;
    @ApiModelProperty("考试成绩")
    private Integer examAchievement;
    @ApiModelProperty("删除标志")
    private Integer deleFlag;
    @ApiModelProperty("是否通过1通过2未通过")
    private Integer ifWhether;
    @ApiModelProperty("考试开始时间")
    private Date startTime;

    @ApiModelProperty("考试开始时间dateStart")
    private Date dateStart;
    @ApiModelProperty("考试开始时间dateEnd")
    private Date dateEnd;

    private  String testUserName;
    @ApiModelProperty("单位名称")
    private String unitName;
    @ApiModelProperty("部门名称")
    private String departMentName;



}