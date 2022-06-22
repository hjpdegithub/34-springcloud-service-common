package com.springboot.boot.modules.admin.dto.curriculum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CurriculumAddOrUpdateDto
 * @Description TODO 课程的新增以及修改参数对象
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:53
 * @Version 1.0
 **/
@Data
@ApiModel("CurriculumAddOrUpdateDto对象")
public class CurriculumAddOrUpdateDto {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("课程名称")
    private String curriculumName;

    @ApiModelProperty("一级分类id")
    private Long genFirstClassifyId;

    @ApiModelProperty("二级分类id")
    private Long genSecondClassifyId;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("学习时限")
    private Date studyTime;

    @ApiModelProperty("课时")
    private Integer classHour;

    @ApiModelProperty("课程格式1文本2视频")
    private Integer classFormat;

    @ApiModelProperty("作者")
    private String authorName;

    @ApiModelProperty("是否需要申请 0否1是")
    private Integer applyType;

    @ApiModelProperty("知识点数量")
    private Integer num;

    @ApiModelProperty("是否是制订课程 1否2是")
    private Integer customizedType;

    @ApiModelProperty("一级制订分类")
    private Long custFirstClassifyId;

    @ApiModelProperty("二级制订分类")
    private Long custSecondClassifyId;

    @ApiModelProperty("是否为公开课 0否1是")
    private Integer openClassType;

    @ApiModelProperty("userId")
    private Long userId;

    @ApiModelProperty("附件id")
    private Long fileId;

    //版本2.0加入字段======================
    @ApiModelProperty("分类属性1不同2认证")
    private Integer propertyType;
    @ApiModelProperty("认证一级分类")
    private Long authFirstClassifyId;
    @ApiModelProperty("认证二级分类")
    private Long authSencondClassifyId;





}
