package com.springboot.boot.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName QuestionBankPracticeSBDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 14:46
 * @Version 1.0
 **/
@Data
@ApiModel("QuestionBankPracticeSBDto")
public class QuestionBankPracticeSBDto {
    @ApiModelProperty("题库id")
    private Long id;
    @ApiModelProperty("userId")
    private Long userId;
    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("选项id")
    private List<Long> optionId;
//    @ApiModelProperty("正确答案")
//    private String rightAnswer;
//
//    @ApiModelProperty("是否正确0否1是")
//    private Integer type;
}
