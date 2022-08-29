package com.springboot.boot.modules.admin.vo.examination;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpExaminationRule;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ExaminationAndRuleVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/8 0008 10:58
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationAndRuleVo")
public class ExaminationAndRuleVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private Integer examinationType;

    private Integer rangeType;

    private String name;

    private Date startTime;

    private Date endTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;

    private String subject;

    private Integer frequencyCount;

    private Integer paper;

    private Integer passingMark;

    private Integer timeLengh;

    private Date upTime;

    private Integer singleChoiceNum;

    private Integer multipleChoiceNum;

    private Integer judgeNum;
    private Integer upType;

    private List<MpExaminationRule> rules;

    //分析题版本
    private Integer analysisNum;

}
