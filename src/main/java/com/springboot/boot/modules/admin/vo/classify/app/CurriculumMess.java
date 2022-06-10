package com.springboot.boot.modules.admin.vo.classify.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CurriculumMess
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/22 0022 15:21
 * @Version 1.0
 **/
@Data
public class CurriculumMess {

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("课程主键id")
    private Long CurriId;

    @ApiModelProperty("课程名称")
    private String secondClassifyName;
}
