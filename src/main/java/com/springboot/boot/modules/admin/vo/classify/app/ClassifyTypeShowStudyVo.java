package com.springboot.boot.modules.admin.vo.classify.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SearchStudyVo
 * @Description TODO 自助学习列表返回报文
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:18
 * @Version 1.0
 **/
@Data
@Api("SearchStudyVo对象")
public class ClassifyTypeShowStudyVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("一级分类主键id")
    private Long firstId;

    @ApiModelProperty("分类名称")
    private String firstClassifyName;

    @ApiModelProperty("分类描述")
    private String firstClassifyDescr;

    @ApiModelProperty("总课时")
    private Integer countNum;

    @ApiModelProperty("课程类型")
    private Integer typeNum;

    @ApiModelProperty("知识点数")
    private Integer sumNum;

    @ApiModelProperty("课程总数")
    private Integer curriculumNum;
    @ApiModelProperty("视频数")
    private Integer vNum;

    @ApiModelProperty("文档数")
    private Integer dNum;

    @ApiModelProperty("二级分类信息包括课程")
    private List<SecondClassify> secondClassifies;




    /**
     * 二级分类参数对象
     */
    @Data
    public static class SecondClassify {

        @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
        @ApiModelProperty("二级分类主键id")
        private Long secondId;

        @ApiModelProperty("二级分类名称")
        private String secondClassifyName;

        private List<CurriculumMess> curriculumMesses;
    }

}
