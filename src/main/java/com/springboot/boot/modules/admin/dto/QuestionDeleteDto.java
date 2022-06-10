package com.springboot.boot.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName QuestionDeleteDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 14:02
 * @Version 1.0
 **/
@Data
@ApiModel("QuestionDeleteDto")
public class QuestionDeleteDto {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("id")
    private List<Long> ids;

}
