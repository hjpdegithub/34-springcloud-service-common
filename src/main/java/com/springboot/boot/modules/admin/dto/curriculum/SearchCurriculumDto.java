package com.springboot.boot.modules.admin.dto.curriculum;


import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SearchCurriculumDto
 * @Description TODO 课程查询参数对象
 * @Author jhzhou
 * @Date 2022/3/14 0014 11:04
 * @Version 1.0
 **/
@Data
@ApiModel("SearchCurriculumDto对象1")
public class SearchCurriculumDto extends PageDto {
    @ApiModelProperty("课程名称")
    private String curriculumName;
    @ApiModelProperty("课程格式1为文本格式2为视频格式")
    private Integer classFormat;
}
