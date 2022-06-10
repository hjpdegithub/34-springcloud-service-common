package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpSecondClassify {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String secondClassifyName;

    private String secondClassifyDescr;

    private Integer secondClassifyType;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long firstClassifyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondClassifyName() {
        return secondClassifyName;
    }

    public void setSecondClassifyName(String secondClassifyName) {
        this.secondClassifyName = secondClassifyName == null ? null : secondClassifyName.trim();
    }

    public String getSecondClassifyDescr() {
        return secondClassifyDescr;
    }

    public void setSecondClassifyDescr(String secondClassifyDescr) {
        this.secondClassifyDescr = secondClassifyDescr == null ? null : secondClassifyDescr.trim();
    }

    public Integer getSecondClassifyType() {
        return secondClassifyType;
    }

    public void setSecondClassifyType(Integer secondClassifyType) {
        this.secondClassifyType = secondClassifyType;
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

    public Long getFirstClassifyId() {
        return firstClassifyId;
    }

    public void setFirstClassifyId(Long firstClassifyId) {
        this.firstClassifyId = firstClassifyId;
    }
}