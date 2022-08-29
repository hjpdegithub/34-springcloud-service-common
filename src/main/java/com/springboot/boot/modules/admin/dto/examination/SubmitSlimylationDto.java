package com.springboot.boot.modules.admin.dto.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SubmitSlimylationDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/18 0018 10:26
 * @Version 1.0
 **/
@Data
@ApiModel("SubmitSlimylationDto")
public class SubmitSlimylationDto {

    @ApiModelProperty("userId")
    private Long userId;
    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("考试名称")
    private String examName;
    @ApiModelProperty
    private List<QuaestVo> quaestVos;
    @ApiModelProperty("考试类型1模拟2考试")
    private Integer examType;
    @ApiModelProperty("考试用时")
    private Integer examTime;



    @Data
    public static class QuaestVo {
        @ApiModelProperty("题库id")
        private Long id;
        @ApiModelProperty("选项id")
        private List<Long> optionId;
        @ApiModelProperty("试题类型，1单选2多选3判断4解析")
        private Integer type;
        //分析题版本加入字段
        @ApiModelProperty("用户分析题答案")
        private String userAnalysisAnswer;
}
}
