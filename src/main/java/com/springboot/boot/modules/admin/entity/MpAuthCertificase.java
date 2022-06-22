package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpAuthCertificase {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long certificateId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long crateUser;

    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTiime;

    private Integer deleFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
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
}