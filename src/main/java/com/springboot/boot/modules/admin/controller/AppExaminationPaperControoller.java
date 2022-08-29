package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.QuestionBankPracticeSBDto;
import com.springboot.boot.modules.admin.dto.examination.ExaminationAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import com.springboot.boot.modules.admin.dto.sign.ChilckSignUpDto;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.ExaminationPaperService;
import com.springboot.boot.modules.admin.vo.examination.AppQuestionBankExamVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName AppExaminationPaperControoller
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 14:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/app/examinationPaper")
@Slf4j
@Api(tags = "2.4", description = "前端试卷先关接口【周京昊】")
@CrossOrigin
public class AppExaminationPaperControoller {
    @Resource
    private ExaminationPaperService paperService;
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "考试详情接口", notes="考试详情接口")
    @GetMapping(value="/detail")
    public ApiResult detail(@RequestParam Long id,@RequestParam Long userId){
        if (null == userId){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("考试详情接口========={}userId{}", JSONObject.toJSON(id),userId);
        ApiResult result = paperService.detail(id,userId);
        return result;
    }



    @ApiOperation(value = "点击报名或取消报名接口", notes="点击报名或取消报名接口")
    @PostMapping(value="/chlickSignUp")
    public ApiResult calickSignUp(@RequestBody ChilckSignUpDto dto){
        if (null == dto.getUserId()){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("点击报名接口========={}", JSONObject.toJSON(dto));
        ApiResult result = paperService.calickSignUp(dto);
        return result;
    }

    @ApiOperation(value = "题库练习", notes="题库练习")
    @GetMapping(value="/questionBankPractice")  
    public ApiResult questionBankPractice(@RequestParam Long id){
        log.info("题库练习========={}", JSONObject.toJSON(id));
        ApiResult result = paperService.questionBankPractice(id);
        return result;
    }


    @ApiOperation(value = "查看问卷", notes="查看问卷")
    @GetMapping(value="/questionnaireQuery")
    public ApiResult questionnaireQuery(@RequestParam Long id){
        log.info("查看问卷========={}", JSONObject.toJSON(id));
        ApiResult result = paperService.questionnaireQuery(id);
        return result;
    }



    @ApiOperation(value = "题库练习交卷", notes="题库练习交卷")
    @PostMapping(value="/questionBankPracticeSubmit")
    public ApiResult questionBankPracticeSubmit(@RequestBody QuestionBankPracticeSBDto dto){
        if (null == dto.getUserId()){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("题库练习交卷========={}", JSONObject.toJSON(dto));
        ApiResult result = paperService.questionBankPracticeSubmit(dto);
        return result;
    }

    @ApiOperation(value = "错题查询", notes="错题查询")
    @GetMapping(value="/searchErrorQuestion")
    public ApiResult searchErrorQuestion(@RequestParam Long userId ,Long examId){
        if (null == userId){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("题库练习========={}---{}", userId,examId);
        ApiResult result = paperService.searchErrorQuestion(userId,examId);
        return result;
    }

    @ApiOperation(value = "模拟在线考试", notes="模拟在线考试")
    @GetMapping(value="/selectSimulation")
    public ApiResult selectSimulation(@RequestParam Long id){
        log.info("模拟考试========={}", id);
        Integer type = 0;
        ApiResult result = paperService.selectSimulation(id,type);
        return result;
    }

    @ApiOperation(value = "认证考试", notes="认证考试")
    @GetMapping(value="/selectSimulationByAuth")
    public ApiResult selectSimulationByAuth(@RequestParam Long id,@RequestParam Long authId,@RequestParam Long userId){
        log.info("模拟考试========={}", id);
        Integer auth = authService.ifWhere(authId,userId);
        if (auth.intValue() == 1){
            return ApiResult.error("请先完成所有的课程学习！");
        }
        Integer type = 1;
        ApiResult result = paperService.selectSimulation(id,type);
        AppQuestionBankExamVo data = (AppQuestionBankExamVo) result.getData();
        data.setAuthId(authId);
        data.setUserId(userId);
        return result;
    }

    @ApiOperation(value = "在线考试交卷", notes="在线考试")
    @PostMapping(value="/submitSimulation")
    public ApiResult submitSimulation(@RequestBody SubmitSlimylationDto dto){
        if (null == dto.getUserId()){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("模拟在线考试交卷========={}", dto);
        ApiResult result = paperService.submitSimulation(dto);
        return result;
    }

    @ApiOperation(value = "模拟考试交卷", notes="模拟考试")
    @PostMapping(value="/submitSimulationByMoni")
    public ApiResult submitSimulationByMoni(@RequestBody SubmitSlimylationDto dto){
        if (null == dto.getUserId()){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("模拟在线考试交卷========={}", dto);
        ApiResult result = paperService.submitSimulationByMoni(dto);
        return result;
    }

    @ApiOperation(value = "历史考试成绩", notes="历史考试成绩")
    @GetMapping(value="/searchGrade")
    public ApiResult searchGrade(@RequestParam("userId") Long userId,@RequestParam("id") Long id){
        if (null == userId){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("模拟考试=========user{},id{}", userId,id);
        ApiResult result = paperService.searchGrade(userId,id);
        return result;
    }

    @ApiOperation(value = "试卷查看", notes="试卷查看")
    @GetMapping(value="/searchExamById")
    public ApiResult searchExamById(@RequestParam("userId") Long userId,@RequestParam("id") Long id,@RequestParam("achievementId") Long achievementId){
        if (null == userId){
            ApiResult.error(500,"请先验证用户信息!");
        }
        log.info("模拟考试=========user{},id{},achievementId{}", userId,id,achievementId);
        ApiResult result = paperService.searchExamById(userId,id,achievementId);
        return result;
    }

    }
