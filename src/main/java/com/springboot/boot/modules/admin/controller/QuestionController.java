package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.QuestionDeleteDto;
import com.springboot.boot.modules.admin.dto.QuestionSerchDto;
import com.springboot.boot.modules.admin.dto.examination.ExaminationSearchDto;
import com.springboot.boot.modules.admin.service.ExaminationPaperService;
import com.springboot.boot.modules.admin.service.QuestionService;
import com.springboot.boot.modules.admin.vo.examination.ExaminationVo;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName QuestionnaireController
 * @Description TODO 题库管理
 * @Author jhzhou
 * @Date 2022/4/8 0008 10:33
 * @Version 1.0
 **/

@RestController
@RequestMapping("/mp/question")
@Slf4j
@Api(tags = "2.3", description = "题库管理【周京昊】")
@CrossOrigin
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private ExaminationPaperService paperService;


    @ApiOperation(value = "题库的新增及修改", notes="题库的新增及修改")
    @PostMapping(value="/addOrUpdate")
    public ApiResult addOrUpdate(@RequestBody QuestionBankAddAndUpdateDto dto){
        log.info("题库的新增及修改========={}", JSONObject.toJSON(dto));
        if (dto.getType() != 4 && dto.getOptionDtos().isEmpty()){
            throw new BusinessException("选项内容不能为空");
        }
        //新增题库判断试卷题数量是否上限 上限提示不让添加
        ApiResult result1 = paperService.checkQuestionByExamId(dto);
        if (result1.getCode()== 500){
            return result1;
        }
        ApiResult result = questionService.addOrUpdate(dto);
        return result;
    }

    @ApiOperation(value = "题库管理查询接口", notes="题库管理查询接口")
    @PostMapping(value="/search")
    public ApiResult search(@RequestBody QuestionSerchDto dto){
        log.info("题库管理查询接口========={}", JSONObject.toJSON(dto));
        PageInfo<QuestionSearchVo> result = questionService.search(dto);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "题库回显接口", notes="题库回显接口")
    @GetMapping(value="/searchById")
    public ApiResult searchById(@RequestParam("id") Long id ){
        log.info("题库回显接口========={}", JSONObject.toJSON(id));
        QuestionSearchVo result = questionService.searchById(id);
        return ApiResult.success(result);
    }


    @ApiOperation(value = "题库的删除", notes="题库的删除")
    @PostMapping(value="/delete")
    public ApiResult delete(@RequestBody QuestionDeleteDto deleteDto){
        log.info("题库的删除========={}", JSONObject.toJSON(deleteDto));
        ApiResult result = questionService.delete(deleteDto);
        return result;
    }
}
