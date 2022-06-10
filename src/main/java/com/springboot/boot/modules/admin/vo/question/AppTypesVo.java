package com.springboot.boot.modules.admin.vo.question;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AppTypesVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 10:36
 * @Version 1.0
 **/
@Data
public class AppTypesVo {
    @ApiModelProperty("1单选2多选3判断")
    private Integer type;

    @ApiModelProperty
    private Sum count;
    @ApiModelProperty("题干")
    private List<AppQuestionVo> appQuestionVos;

    @Data
    public static class Sum {
        private Integer num;
        private Integer points;
        private Integer sum;
    }
}
