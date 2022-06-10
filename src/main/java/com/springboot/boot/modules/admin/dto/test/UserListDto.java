package com.springboot.boot.modules.admin.dto.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserListDto {
    @ApiModelProperty("userId")
    private Long id;

    @ApiModelProperty("用户姓名")
    private String userName;
}