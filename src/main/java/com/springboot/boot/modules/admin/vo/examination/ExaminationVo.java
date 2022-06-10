package com.springboot.boot.modules.admin.vo.examination;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ExaminationVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:40
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationVo")
public class ExaminationVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("试卷id")
    private Long id;
    @ApiModelProperty("试卷名称")
    private String name;

    @ApiModelProperty("考试类型")
    private Integer examinationType;

    @ApiModelProperty("考试范围")
    private Integer rangeType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;


    @ApiModelProperty("单位名称")
    private String unitName;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("上线状态")
    private Integer upType;
}
