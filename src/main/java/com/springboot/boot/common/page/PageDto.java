package com.springboot.boot.common.page;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author:lijx
 * @(#)PageDto 1.0 2020/3/20
 * <p>
 * Copyright (c) 2020, PICCHEALTH. All rights reserved.
 * PICCHEALTH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
@Data
@ApiModel("PageDto")
public class PageDto implements Serializable {

    private static final long serialVersionUID = -6214238099662724336L;
    @ApiModelProperty(value = "页码",example = "1")
    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @ApiModelProperty(value = "每页条数",example = "10")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;
    @ApiModelProperty("是否分页（true:分页、false:不分页）【注：默认true】")
    private Boolean paging = true;
}
