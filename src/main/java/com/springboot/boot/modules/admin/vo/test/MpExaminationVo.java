package com.springboot.boot.modules.admin.vo.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MpExaminationVo {
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
    private Long crateUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    private Integer singleChoiceNum;
    private Integer multipleChoiceNum;
    private Integer judgeNum;
    private Integer upType;
    List<MpQuestionBankVo> mpQuestionBankVoList ;
    //2.0版本--------------
    //1考试进行中2考试已结束3考试未开始
    private Integer examTimeType;
    //报名人数
    private Integer signUpCount;

}