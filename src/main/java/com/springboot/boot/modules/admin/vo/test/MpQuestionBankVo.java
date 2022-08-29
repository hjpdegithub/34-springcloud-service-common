package com.springboot.boot.modules.admin.vo.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class MpQuestionBankVo   extends BaseRowModel {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ExcelProperty("题干")
    private String name;
    @ExcelProperty("类型")
    private Integer type;
    //试卷ID
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examinationId;
    //正确答案
    @ExcelProperty("正确答案")
    private String rightAnswer;
    //答案选项
    @ExcelProperty("选项")
    private String choice;
    public String getChoice() {
        return choice;
    }
    public void setChoice(String choice) {
        this.choice = choice == null ? null : choice.trim();
    }
    @ExcelProperty("解析")
    private  String analysisQuestion;

    @ExcelProperty("分析题答案")
    private String analysisAnswer;

    List<MpOptionVo> mpOptionVoList ;
     //统计用map
    private HashMap statisticsMap;
    private Integer total;

    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Long getExaminationId() {
        return examinationId;
    }
    public void setExaminationId(Long examinationId) {
        this.examinationId = examinationId;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer == null ? null : rightAnswer.trim();
    }
    public Long getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getDeleFlag() {
        return deleFlag;
    }
    public void setDeleFlag(Integer deleFlag) {
        this.deleFlag = deleFlag;
    }
    public String getAnalysisQuestion() {
        return analysisQuestion;
    }
    public void setAnalysisQuestion(String analysisQuestion) {
        this.analysisQuestion = analysisQuestion;
    }
}