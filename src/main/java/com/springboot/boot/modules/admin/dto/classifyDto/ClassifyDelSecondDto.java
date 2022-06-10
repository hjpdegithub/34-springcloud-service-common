package com.springboot.boot.modules.admin.dto.classifyDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ClassifyDelDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/11 0011 17:44
 * @Version 1.0
 **/
@Data
@ApiModel("ClassifyDelSecondDto")
public class ClassifyDelSecondDto {
    @ApiModelProperty("二级分了集合id")
    private List<Long> ids;
}
