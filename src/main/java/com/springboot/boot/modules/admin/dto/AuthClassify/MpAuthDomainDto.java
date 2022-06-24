package com.springboot.boot.modules.admin.dto.AuthClassify;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MpAuthDomainDto {

    @ApiModelProperty("认证领域主键")
    private Long id;
    @ApiModelProperty("认证方向关联键")
    private Long authDirectionId;
    @ApiModelProperty("认证领域名称")
    private String name;
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

}