package com.springboot.boot.modules.admin.dto.curriculum;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CurThumDto {

    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("课程ID")
    private Long curId;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("创建者")
    private Long createUser;
    @ApiModelProperty("创建日期")
    private Date createDate;
    @ApiModelProperty("更新者")
    private Long updateUser;
    @ApiModelProperty("更新日期")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurId() {
        return curId;
    }

    public void setCurId(Long curId) {
        this.curId = curId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}