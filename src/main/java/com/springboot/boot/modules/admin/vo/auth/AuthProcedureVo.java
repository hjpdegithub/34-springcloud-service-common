package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.vo.curriculum.AuthClassVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AuthProcedureVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/23 0023 9:22
 * @Version 1.0
 **/
@Data
@ApiModel("AuthProcedureVo对象")
public class AuthProcedureVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("认证id")
    private Long id;
    @ApiModelProperty("认证名称")
    private String name;
    @ApiModelProperty("描述")
    private String authDescr;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("用户id")
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("试卷id")
    private Long examId;
    @ApiModelProperty("课程学习列表")
    private List<AuthClassVo> classVoList;

    @ApiModelProperty("考试次数")
    private Integer examCount;

//    @ApiModelProperty("是否报名0否1是")
//    private Integer signUpType;
//
//    @ApiModelProperty("是否学习0否1是")
//    private Integer studyType;
//
//    @ApiModelProperty("是否可以考试0否1是")
//    private Integer examType;
//
//    @ApiModelProperty("是否领取证书0否1是")
//    private Integer certificaseType;
    @ApiModelProperty("完成类型  0初始1学习2考试3证书4全部完成5全部置灰")
    private Integer finishType;

}
