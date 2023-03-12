package com.springboot.boot.modules.admin.vo.Index;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("IndexDto")
public class IndexVo {

    @ApiModelProperty("附件id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("管理id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long manageId;
    @ApiModelProperty("指标名称")
    private String name;
    @ApiModelProperty("指标值")
    private String value;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("删除标志")
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




}
