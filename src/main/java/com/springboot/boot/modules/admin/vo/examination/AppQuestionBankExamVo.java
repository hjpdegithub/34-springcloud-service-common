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
@ApiModel("AppQuestionBankExamVo")
public class AppQuestionBankExamVo {
    @ApiModelProperty("试卷id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("试卷名称")
    private String name;
    @ApiModelProperty("1单选2多选3判断")
    private List<AppTypesVo> appTypesVos;
    @ApiModelProperty("总分")
    private Integer totalPoints;
    @ApiModelProperty("题量")
    private Integer examCount;

    private Single single;
    private Multiple multiple;
    private Judge judge;

    //--------------------2.0
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("认证id")
    private Long authId;

    @Data
    public static class Single {
        private Integer num;
        private Integer points;
        private Integer sum;
    }
    @Data
    public static class  Multiple{
        private Integer num;
        private Integer points;
        private Integer sum;
    }
    @Data
    public static class Judge {
        private Integer num;
        private Integer points;
        private Integer sum;
    }
}
