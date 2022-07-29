package com.springboot.boot.modules.admin.vo.curriculum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SearchCurrAndSencondClassVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/7/27 0027 10:40
 * @Version 1.0
 **/
@Data
public class SearchCurrAndSencondClassVo {
    @ApiModelProperty("二级分类id")
    private Long id;
    @ApiModelProperty("二级分类名称")
    private String secondClassifyName ;
    @ApiModelProperty("课程id")
    private Long currId;
    @ApiModelProperty("课程格式 1为文本格式2为视频格式")
    private Integer classFormat;
    @ApiModelProperty("课程名称")
    private String curriculumName;
    private Long custSecondClassifyId;
    private Long genSecondClassifyId;


}
