package com.springboot.boot.modules.admin.vo.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AppQuestionBankPracticeSubmitVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 15:06
 * @Version 1.0
 **/
@Data
@ApiModel("AppQuestionBankPracticeSubmitVo")
public class AppQuestionBankPracticeSubmitVo {
    @ApiModelProperty("正确答案")
    private List<String> rightAnswer;

    @ApiModelProperty("是否正确0否1是")
    private Integer type;
}
