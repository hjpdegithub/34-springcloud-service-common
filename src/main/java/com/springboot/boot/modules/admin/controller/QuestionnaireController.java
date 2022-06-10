package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllPageDto;
import com.springboot.boot.modules.admin.service.QuestionnaireService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



//    @PostMapping(value = "/questionnaireListWithOutOption")
//    public ApiResult questionnaireList(
//    ) {
//        return ApiResult.success(questionnaireService.questionnaireList());
//    }

    //
//    @PostMapping(value = "/questionnaireDetail")
//    public ApiResult questionnaireDetail(@RequestBody CommonAllDto dto) {
//        return ApiResult.success(questionnaireService.questionnaireDetail(dto));
//    }





}
