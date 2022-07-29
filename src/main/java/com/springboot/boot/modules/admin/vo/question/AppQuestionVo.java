package com.springboot.boot.modules.admin.vo.question;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AppQuestionVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 10:28
 * @Version 1.0
 **/
@Data
@ApiModel("AppQuestionVo")
public class AppQuestionVo {
    @ApiModelProperty("题库id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    @ApiModelProperty("题干信息")
    private String name;

    @ApiModelProperty("正确答案")
    private List<String> rightAnswer;

    @ApiModelProperty("解析")
    private String analysisQuestion;

    @ApiModelProperty("试卷主键id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examinationId;

    @ApiModelProperty("用乎选项id")
//    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private List<String> userOptionId;

    @ApiModelProperty("选项集合")
    private List<AppOptionVo> appOptionVos;

}
