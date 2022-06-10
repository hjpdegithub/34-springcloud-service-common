package com.springboot.boot.modules.admin.vo.examination;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.vo.question.AppTypesVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AppQuestionBankPracticeVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 10:23
 * @Version 1.0
 **/
@Data()
@ApiModel("AppQuestionBankPracticeVo")
public class AppQuestionBankPracticeVo {
    @ApiModelProperty("试卷id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("试卷名称")
    private String name;
    @ApiModelProperty("1单选2多选3判断")
    private List<AppTypesVo> appTypesVos;
}
