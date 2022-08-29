package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpExamAchievement {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;

    private Integer type;

    private Integer examTime;

    private Integer examAchievement;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long crateUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTiime;

    private Integer deleFlag;

    private Integer ifWhether;

    private Integer judgeGrade;

    private Integer singleGrade;

    private Integer multipleGrade;

    private Integer analysisGrade;

    private Integer countGrade;

    private Integer showType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getExamAchievement() {
        return examAchievement;
    }

    public void setExamAchievement(Integer examAchievement) {
        this.examAchievement = examAchievement;
    }

    public Long getCrateUser() {
        return crateUser;
    }

    public void setCrateUser(Long crateUser) {
        this.crateUser = crateUser;
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

    public Date getUpdateTiime() {
        return updateTiime;
    }

    public void setUpdateTiime(Date updateTiime) {
        this.updateTiime = updateTiime;
    }

    public Integer getDeleFlag() {
        return deleFlag;
    }

    public void setDeleFlag(Integer deleFlag) {
        this.deleFlag = deleFlag;
    }

    public Integer getIfWhether() {
        return ifWhether;
    }

    public void setIfWhether(Integer ifWhether) {
        this.ifWhether = ifWhether;
    }

    public Integer getJudgeGrade() {
        return judgeGrade;
    }

    public void setJudgeGrade(Integer judgeGrade) {
        this.judgeGrade = judgeGrade;
    }

    public Integer getSingleGrade() {
        return singleGrade;
    }

    public void setSingleGrade(Integer singleGrade) {
        this.singleGrade = singleGrade;
    }

    public Integer getMultipleGrade() {
        return multipleGrade;
    }

    public void setMultipleGrade(Integer multipleGrade) {
        this.multipleGrade = multipleGrade;
    }

    public Integer getAnalysisGrade() {
        return analysisGrade;
    }

    public void setAnalysisGrade(Integer analysisGrade) {
        this.analysisGrade = analysisGrade;
    }

    public Integer getCountGrade() {
        return countGrade;
    }

    public void setCountGrade(Integer countGrade) {
        this.countGrade = countGrade;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }
}