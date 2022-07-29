package com.springboot.boot.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName QuestionBankAddAndUpdateDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/8 0008 16:02
 * @Version 1.0
 **/
@Data
@ApiModel("QuestionBankAddAndUpdateDto")
public class QuestionBankAddAndUpdateDto {
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("题干信息")
    private String name;

    private Integer type;

    private Long examinationId;

    private List<String> rightAnswer;

    private List<OptionDto> optionDtos;

    private Long userId;

    //3.0版本---加入字段
    @ApiModelProperty("解析")
    private String analysisQuestion;
}
