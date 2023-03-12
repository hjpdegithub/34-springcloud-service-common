package com.springboot.boot.modules.admin.dto.Index;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("IndexManageDto")
public class IndexManageDto extends PageDto{
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("ids")
    private List<Long> ids;

    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("上下线")
    private Integer upType;
    @ApiModelProperty("是否删除")
    private Integer delFlag;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("创建者")
    private Long createUser;
    @ApiModelProperty("创建日期")
    private Date createDate;
    @ApiModelProperty("更新者")
    private Long updateUser;
    @ApiModelProperty("更新日期")
    private Date updateDate;
    @ApiModelProperty("指标")
    private List<IndexDto> indexDtos;

}
