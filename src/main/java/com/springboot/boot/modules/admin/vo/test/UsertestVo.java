package com.springboot.boot.modules.admin.vo.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UsertestVo {
    //考试人数统计
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("用户报名表id")
    private Long sinUpExamId;

    @ApiModelProperty("用户考试表id")
    private Long mpUserExamId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("类型1模拟考试2在线考试")
    private Integer type;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("试卷id")
    private Long mpUserExamExamId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("题库id")
    private Long mpUserExamQuestionId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("选项id")
    private Long mpUserExamOptionId;
    @ApiModelProperty("用户考试创建者")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long mpUserExamCreateUser;

    @ApiModelProperty("试卷主键id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("试卷类型：1考试类2问卷类")
    private Integer examinationType;
    @ApiModelProperty("展示范围：1数据中台门户考试2能力开放平台活动")
    private Integer rangeType;
    @ApiModelProperty("试卷名称")
    private String name;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("开放单位id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;
    @ApiModelProperty("部门id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;
    @ApiModelProperty("考试科目")
    private String subject;
    @ApiModelProperty("考试次数")
    private Integer frequencyCount;
    @ApiModelProperty("试卷分数")
    private Integer paper;
    @ApiModelProperty("及格分数")
    private Integer passingMark;
    @ApiModelProperty("考试时长")
    private Integer timeLengh;
    @ApiModelProperty("上线时间")
    private Date upTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("创建人")
    private Long crateUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;
    @ApiModelProperty("用户考试创建者")
    private Date updateTime;
    @ApiModelProperty("用户考试创建者")
    private Integer deleFlag;
    @ApiModelProperty("单选题数量")
    private Integer singleChoiceNum;
    @ApiModelProperty("多选题数量")
    private Integer multipleChoiceNum;
    @ApiModelProperty("判断题数量")
    private Integer judgeNum;

    @ApiModelProperty("报名表的考试时间")
    private Date sinUptime;





}









