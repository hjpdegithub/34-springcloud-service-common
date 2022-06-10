package com.springboot.boot.modules.admin.dto.examination;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ExaminationSearchDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:18
 * @Version 2.0
 **/
@ApiModel("ExaminationSearchDto")
@Data
public class ExaminationSearchNoPageDto {
    @ApiModelProperty("试卷名称")
    private String name;

    @ApiModelProperty("单位")
    private Long unitId;

    @ApiModelProperty("试卷类型")
    private Integer examinationType;

    @ApiModelProperty("开放部门")
    private Long departmentId;

    @ApiModelProperty("展示范围")
    private Integer rangeType;

}
