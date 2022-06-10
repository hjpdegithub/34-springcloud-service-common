package com.springboot.boot.modules.admin.dto.classifyDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SecondClassifyAddOrUpdateDto
 * @Description TODO 二级分类参数对象
 * @Author jhzhou
 * @Date 2022/3/11 0011 14:06
 * @Version 1.0
 **/
@Data
@ApiModel("SecondClassifyAddOrUpdateDto对象")
public class SecondClassifyAddOrUpdateDto {
    @ApiModelProperty("二级主键id")
    private Long id;

    @ApiModelProperty("二级分类名称")
    private String secondClassifyName;

    @ApiModelProperty("二级分类描述")
    private String secondClassifyDescr;
}
