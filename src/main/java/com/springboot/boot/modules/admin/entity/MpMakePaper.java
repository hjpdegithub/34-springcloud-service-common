package com.springboot.boot.modules.admin.entity;

import java.util.Date;

public class MpMakePaper {
    private Long id;

    private String examName;

    private Integer examType;

    private Integer submitTime;

    private Long examId;

    private Long achievementId;

    private Integer statusType;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

    private Integer judgeGrade;

    private Integer singleGrade;

    private Integer multipleGrade;

    private Integer analysisGrade;

    private Integer countGrade;

    private Long userId;

    private Long authId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName == null ? null : examName.trim();
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Integer getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Integer submitTime) {
        this.submitTime = submitTime;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
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
}