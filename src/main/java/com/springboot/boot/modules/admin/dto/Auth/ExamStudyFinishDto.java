package com.springboot.boot.modules.admin.dto.Auth;

import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ClassStudyFinishDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/24 0024 10:27
 * @Version 1.0
 **/
@Data
public class ExamStudyFinishDto extends AuthBaseDto {

    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty
    private List<SubmitSlimylationDto.QuaestVo> quaestVos;
    @ApiModelProperty("考试类型1模拟2考试")
    private Integer examType;
    private Integer examTime;

    private String examName;



    @Data
    public static class QuaestVo {
        @ApiModelProperty("题库id")
        private Long id;
        @ApiModelProperty("选项id")
        private List<Long> optionId;

        private Integer type;
    }
}
