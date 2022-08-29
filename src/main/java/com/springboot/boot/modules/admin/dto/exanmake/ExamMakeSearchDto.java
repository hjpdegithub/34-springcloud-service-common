package com.springboot.boot.modules.admin.dto.exanmake;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ExamMakeSearchDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/8 0008 16:35
 * @Version 1.0
 **/
@Data
public class ExamMakeSearchDto extends PageDto {
    @ApiModelProperty("编号")
    private Integer number;
    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("类型1普通2认证")
    private  Integer examType;
    @ApiModelProperty("1待办理2已办理")
    private  Integer statusType;
}
