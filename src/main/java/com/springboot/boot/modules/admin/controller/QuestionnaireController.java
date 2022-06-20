package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllPageDto;
import com.springboot.boot.modules.admin.service.QuestionnaireService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mp/Questionnaire")
@Slf4j
@Api(tags = "1.5.7", description = "问卷调查接口【侯建鹏】")
@CrossOrigin
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;
    //问卷调查
    @PostMapping(value = "/questionnaireListWithOption")
    public ApiResult questionnaireListWithOption(CommonAllDto dto
    ) {
        return ApiResult.success(questionnaireService.mpQuestionnaireOptionVoList(dto));
    }

    //问卷调查
    @PostMapping(value = "/questionnaireListWithPage")
    public ApiResult questionnaireListWithPage(CommonAllPageDto dto
    ) {
        return ApiResult.success(questionnaireService.questionnaireListWithPage(dto));
    }

    @PostMapping(value="/submitSimulation")
    public ApiResult submitSimulation(@RequestBody SubmitSlimylationDto dto){

        log.info("问卷交卷========={}", dto);
        ApiResult result = questionnaireService.submitSimulation(dto);
        return result;
    }

    @PostMapping(value="/questionnaireStatistics")
    public ApiResult questionnaireStatistics(@RequestBody CommonAllDto dto){

        log.info("问卷统计分析========={}", dto);
        ApiResult result = questionnaireService.questionnaireStatistics(dto);
        return result;
    }





}
