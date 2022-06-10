package com.springboot.boot.modules.admin.dto.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ExaminationRuleDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 15:03
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationRuleDto对象")
public class ExaminationRuleDto {
    @ApiModelProperty("试卷规则id")
    private Long id;

    @ApiModelProperty("题目数量")
    private Integer subjectNum;

    @ApiModelProperty("题目分数")
    private Integer fraction;


}
