package com.springboot.boot.modules.admin.vo.curriculum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SrearchAndClassifyVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/7/27 0027 12:01
 * @Version 1.0
 **/
@Data
public class SrearchAndClassifyVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("二级分类id")
    private Long id;
    @ApiModelProperty("二级分类名称")
    private String secondClassifyName ;
    List<MpCurriculum> mpCurr;

}
