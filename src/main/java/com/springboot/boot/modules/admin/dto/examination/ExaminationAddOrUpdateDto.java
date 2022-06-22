package com.springboot.boot.modules.admin.dto.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName examinationAddOrUpdateDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 14:47
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationAddOrUpdateDto对象")
public class ExaminationAddOrUpdateDto {
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("试卷类型：1考试类2问卷类3认证类")
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
    private Long unitId;

    @ApiModelProperty("开放部门id")
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

//    @ApiModelProperty("上线时间")
//    private Date upTime;


    @ApiModelProperty("单选数量")
    private Integer singleChoiceNum;

    @ApiModelProperty("多选数量")
    private Integer multipleChoiceNum;

    @ApiModelProperty("判断数量")
    private Integer judgeNum;

    @ApiModelProperty("单选规则")
    private ExaminationRuleDto singleRule;

    @ApiModelProperty("多选规则")
    private ExaminationRuleDto multipleRule;

    @ApiModelProperty("判断规则")
    private ExaminationRuleDto judgeRule;

    private Long userId;




}
