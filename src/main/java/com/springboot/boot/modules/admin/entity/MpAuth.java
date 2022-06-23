package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpAuth {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String name;

    private String authDescr;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long directionId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long domainId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;

    private Integer authLevel;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authFirstClassifyId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authSencondClassifyId;

    private Date certificateTime;

    private Integer certificateType;

    private Date endTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

    private Integer upType;

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

    public String getAuthDescr() {
        return authDescr;
    }

    public void setAuthDescr(String authDescr) {
        this.authDescr = authDescr == null ? null : authDescr.trim();
    }

    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Integer getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAuthFirstClassifyId() {
        return authFirstClassifyId;
    }

    public void setAuthFirstClassifyId(Long authFirstClassifyId) {
        this.authFirstClassifyId = authFirstClassifyId;
    }

    public Long getAuthSencondClassifyId() {
        return authSencondClassifyId;
    }

    public void setAuthSencondClassifyId(Long authSencondClassifyId) {
        this.authSencondClassifyId = authSencondClassifyId;
    }

    public Date getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(Date certificateTime) {
        this.certificateTime = certificateTime;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getUpType() {
        return upType;
    }

    public void setUpType(Integer upType) {
        this.upType = upType;
    }
}