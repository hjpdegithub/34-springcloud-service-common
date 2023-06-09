package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpUserAuthExam {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examId;

    private Integer examAchievement;

    private Integer ifWhether;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

    private Integer judgeGrade;

    private Integer singleGrade;

    private Integer multipleGrade;

    private Integer analysisGrade;

    private Integer countGrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Integer getExamAchievement() {
        return examAchievement;
    }

    public void setExamAchievement(Integer examAchievement) {
        this.examAchievement = examAchievement;
    }

    public Integer getIfWhether() {
        return ifWhether;
    }

    public void setIfWhether(Integer ifWhether) {
        this.ifWhether = ifWhether;
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
}