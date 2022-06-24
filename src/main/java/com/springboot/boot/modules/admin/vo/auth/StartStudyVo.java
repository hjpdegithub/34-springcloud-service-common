package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.vo.curriculum.AuthClassVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
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
public class StartStudyVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("认证id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("认证名称")
    private String name;
    @ApiModelProperty("课程学习列表")
    private List<AuthClassVo> classVoList;
    //百分点
    private BigDecimal stduyCount;
    //是否全部完成 1完成0否
    private Integer finishType;

}
