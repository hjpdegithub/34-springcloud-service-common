package com.springboot.boot.modules.admin.vo.makeexam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MakePaperVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/9 0009 14:10
 * @Version 1.0
 **/
@Data
public class MakePaperVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("主键id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题

    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("成绩id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long achievementId;

    @ApiModelProperty("单选")
    private Integer singleGrade;

    @ApiModelProperty("开始类型1普通2认证")
    private Integer examType;

    @ApiModelProperty("多选")
    private Integer multipleGrade;

    @ApiModelProperty("分析")
    private Integer analysisGrade;

    @ApiModelProperty("总分")
    private Integer countGrade;

    @ApiModelProperty("判断")
    private Integer judgeGrade;

    @ApiModelProperty("分析题")
    private List<analysisClass> analysisClass;
    //分析题分数
    private Integer points;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    //认证id
    private Long authId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    //userid
    private Long userId;

    @Data
    public static class analysisClass {
        //题库id
        private Long id;
        //分析题名称
        private String name;
        //正确答案
        private String analysisAnswer;
        //用户答案
        private String userAnalysisAnswer;

    }
}

