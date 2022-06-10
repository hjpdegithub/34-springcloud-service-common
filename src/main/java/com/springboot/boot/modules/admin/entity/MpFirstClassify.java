package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpFirstClassify {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String firstClassifyName;

    private String firstClassifyDescr;

    private Integer firstClassifyType;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstClassifyName() {
        return firstClassifyName;
    }

    public void setFirstClassifyName(String firstClassifyName) {
        this.firstClassifyName = firstClassifyName == null ? null : firstClassifyName.trim();
    }

    public String getFirstClassifyDescr() {
        return firstClassifyDescr;
    }

    public void setFirstClassifyDescr(String firstClassifyDescr) {
        this.firstClassifyDescr = firstClassifyDescr == null ? null : firstClassifyDescr.trim();
    }

    public Integer getFirstClassifyType() {
        return firstClassifyType;
    }

    public void setFirstClassifyType(Integer firstClassifyType) {
        this.firstClassifyType = firstClassifyType;
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
}