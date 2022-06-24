package com.springboot.boot.modules.admin.dto.AuthClassify;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class MpAuthDirectionAddOrUpdateDto {
    @ApiModelProperty("认证方向主键")
    private Long id;
    @ApiModelProperty("认证方向描述")
    private String description;
    @ApiModelProperty("认证方向名称")
    private String name;
    @ApiModelProperty("认证领域列表")
    List<MpAuthDomainDto>   mpAuthDomainDtoList ;
    @ApiModelProperty("认证领域列表")
    private Long   userId;


}