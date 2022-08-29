package com.springboot.boot.modules.admin.dto.exanmake;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MakerPaperButtonDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/10 0010 9:37
 * @Version 1.0
 **/
@Data
public class MakerPaperButtonDto {
    @ApiModelProperty("判卷主键id")
    private Long id;
    @ApiModelProperty("分析题分数")
    private Integer analysisGrade;
    @ApiModelProperty("总分")
    private Integer countGrade;
    @ApiModelProperty("开始类型1普通2认证")
    private Integer examType;
    @ApiModelProperty("成绩id")
    private Long achievementId;
    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("认证id")
    private Long authId;
    @ApiModelProperty("userId")
    private Long userId;

}
