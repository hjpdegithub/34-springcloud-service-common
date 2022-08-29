package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.TestUserDto;
import com.springboot.boot.modules.admin.dto.test.MpExaminationDto;
import com.springboot.boot.modules.admin.dto.test.TestResultDto;
import com.springboot.boot.modules.admin.service.TestResultService;
import com.springboot.boot.modules.admin.vo.test.TestResultPageDto;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName QuestionnaireController
 * @Description TODO 成绩管理
 * @Author jhzhou
 * @Date 2022/4/8 0008 10:33
 * @Version 1.0
 **/

@RestController
@RequestMapping("/mp/test")
@Slf4j
@Api(tags = "1.5.8", description = "成绩管理【侯建鹏】")
@CrossOrigin
public class TestResultController {
    @Autowired
    private TestResultService testResultService;
    @ApiOperation(value = "考试成绩查询", notes = "考试成绩查询")
    @PostMapping(value = "/resultQuery")
    public ApiResult resultQuery(@RequestBody TestResultPageDto dto) {
        log.info("考试成绩查询========={}", JSONObject.toJSON(dto));
        return  ApiResult.success(testResultService.resultQuery(dto))  ;
    }

    @ApiOperation(value = "考试成绩导出", notes = "考试成绩导出")
    @PostMapping(value = "/export")
    public void export(HttpServletResponse response ,  @RequestBody TestResultDto dto) {
        log.info("考试成绩导出========={}", JSONObject.toJSON(dto));
        testResultService.export(response, dto)  ;
    }

    @ApiOperation(value = "试卷列表查询", notes = "试卷列表查询")
    @PostMapping(value = "/mpExaminationList")
    public ApiResult mpExaminationList(@RequestBody MpExaminationDto dto) {
        log.info("考试成绩查询========={}", JSONObject.toJSON(dto));
        return  ApiResult.success(testResultService.mpExaminationList(dto))  ;
    }

    @ApiOperation(value = "考试人列表查询", notes = "考试人列表查询")
    @PostMapping(value = "/testUserList")
    public ApiResult testUserList(@RequestBody TestUserDto dto) {
        log.info("考试成绩查询========={}", JSONObject.toJSON(dto));
        return  ApiResult.success(testResultService.testUserList(dto))  ;
    }


}
