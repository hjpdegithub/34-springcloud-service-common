package com.springboot.boot.modules.admin.dto.classifyDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ClassifyAddOrUpdate
 * @Description TODO 分类的新增以及修改参数对象
 * @Author jhzhou
 * @Date 2022/3/11 0011 14:01
 * @Version 1.0
 **/
@Data
@ApiModel("ClassifyAddOrUpdateDto对象")
public class ClassifyAddOrUpdateDto {
    @ApiModelProperty("一级主键id")
    private  Long id;
    @ApiModelProperty("分类类型 1为普通分类2位定制分类")
    private Integer classifyType;
    @ApiModelProperty("一级分类名称")
    private String firstClassifyName;
    @ApiModelProperty("一级分类描述")
    private String firstClassifyDescr;
    @ApiModelProperty("二级分类参数对象")
    private List<SecondClassifyAddOrUpdateDto> addOrUpdateDtos;
    @ApiModelProperty("用户id")
    private Long userId;
}
