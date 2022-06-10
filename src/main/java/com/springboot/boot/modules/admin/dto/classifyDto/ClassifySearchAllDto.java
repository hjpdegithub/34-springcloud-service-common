package com.springboot.boot.modules.admin.dto.classifyDto;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassifySearchAllDto extends PageDto {
    @ApiModelProperty("一级分类id")
    private Long firstClassifyId;
}
