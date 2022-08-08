package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AuthAndUserVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/27 0027 9:36
 * @Version 1.0
 **/
@Data
public class AuthAndUserVo {
    @ApiModelProperty("用户认在主键id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("认证名称")
    private String name;
    @ApiModelProperty("认证id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authId;
    @ApiModelProperty("用户id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;
    @ApiModelProperty("用户名称")
    private String userName;
}
