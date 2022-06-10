package com.springboot.boot.modules.admin.vo.examination;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SubimtExamVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/5/19 0019 16:20
 * @Version 1.0
 **/
@Data
public class SubimtExamVo {
    @ApiModelProperty("单选题数量")
    private Integer singleChoiceNum;
    @ApiModelProperty("多选题数量")
    private Integer multipleChoiceNum;
    @ApiModelProperty("判断题数量")
    private Integer judgeNum;
    @ApiModelProperty("成绩")
    private Integer examAchievement;

}
