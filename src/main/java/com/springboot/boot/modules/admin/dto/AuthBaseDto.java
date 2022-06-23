package com.springboot.boot.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AuthBaseDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/23 0023 17:09
 * @Version 1.0
 **/
@Data
@ApiModel("base")
public class AuthBaseDto {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("认证id")
    private Long authId;
}
