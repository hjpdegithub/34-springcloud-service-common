package com.springboot.boot.modules.admin.dto.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MpExaminationDto {
    private Long id;
    private Integer examinationType;
    private Integer rangeType;
    private String name;
    private Date startTime;
    private Date endTime;
    private Long unitId;
    private Long departmentId;
    private String subject;
    private Integer frequencyCount;
    private Integer paper;
    private Integer passingMark;
    private Integer timeLengh;
    private Date upTime;
    private Long crateUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    private Integer singleChoiceNum;
    private Integer multipleChoiceNum;
    private Integer judgeNum;
    private Integer upType;

}