package com.springboot.boot.modules.admin.dto.Auth;

import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ClassStudyFinishDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/24 0024 10:27
 * @Version 1.0
 **/
@Data
public class ClassStudyFinishDto extends AuthBaseDto {
    @ApiModelProperty("课程id")
    private Long classId;
}
