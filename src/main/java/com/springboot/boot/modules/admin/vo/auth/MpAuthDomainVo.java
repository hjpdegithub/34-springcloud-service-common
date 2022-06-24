package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpAuthDomainVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authDirectionId;

    private String name;
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

    public Long getAuthDirectionId() {
        return authDirectionId;
    }

    public void setAuthDirectionId(Long authDirectionId) {
        this.authDirectionId = authDirectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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