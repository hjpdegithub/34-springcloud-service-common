package com.springboot.boot.modules.admin.vo.curriculum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AuthClassVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/23 0023 9:26
 * @Version 1.0
 **/
@Data
public class AuthClassVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("课程id")
    private Long id;
    @ApiModelProperty("课程名称")
    private String curriculumName;
    @ApiModelProperty("课程格式 1为文本格式2为视频格式")
    private Integer classFormat;
    @ApiModelProperty("学习状态1完成2未完成")
    private Integer status;

}
