package com.springboot.boot.modules.admin.dto.AuthClassify;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MpAuthDirectionDto {
    @ApiModelProperty("认证方向主键")
    private Long id;
    @ApiModelProperty("认证方向描述")
    private String description;
    @ApiModelProperty("认证方向名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}