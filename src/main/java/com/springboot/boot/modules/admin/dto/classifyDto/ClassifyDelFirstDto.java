package com.springboot.boot.modules.admin.dto.classifyDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ClassifyDelFirstDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/11 0011 18:14
 * @Version 1.0
 **/
@Data
@ApiModel("ClassifyDelFirstDto")
public class ClassifyDelFirstDto {

    @ApiModelProperty("一级分类id")
    private Long id;
}
