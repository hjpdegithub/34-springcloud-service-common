package com.springboot.boot.modules.admin.dto;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName QuestionSerchDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 9:40
 * @Version 1.0
 **/
@Data
@ApiModel("QuestionSerchDto")
public class QuestionSerchDto extends PageDto {
    @ApiModelProperty("试卷类型")
    private Integer examinationType;

    @ApiModelProperty("试题类型")
    private Integer type;

    @ApiModelProperty("试卷id")
    private Long examinationId;

    @ApiModelProperty("题干信息")
    private String name;
}
