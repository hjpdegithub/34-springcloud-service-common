package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.exanmake.ExamMakeSearchDto;
import com.springboot.boot.modules.admin.dto.exanmake.MakerPaperButtonDto;
import com.springboot.boot.modules.admin.service.ExamMakePaperService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName ExamMakePaperController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/8 0008 16:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/make/paper")
@Slf4j
@Api(tags = "2.8", description = "判卷管理【周京昊】")
@CrossOrigin
public class ExamMakePaperController {

    @Resource
    private ExamMakePaperService examMakePaperService;

    @ApiOperation(value = "判断查询接口", notes="判断查询接口")
    @PostMapping(value="/searchExamMake")
    public ApiResult searchExamMake(@RequestBody ExamMakeSearchDto makeSearchDto){

        ApiResult result = examMakePaperService.searchExamMake(makeSearchDto);
        return result;
    }


    @ApiOperation(value = "判卷接口", notes="判卷接口")
    @GetMapping(value="/makePaperSubmit")
    public ApiResult makePaper(@RequestParam Long id){
        ApiResult result = examMakePaperService.makePaper(id);
        return result;
    }


    @ApiOperation(value = "判卷提交接口", notes="判卷提交接口")
    @PostMapping(value="/makePaperButton")
    public ApiResult makePaperButton(@RequestBody MakerPaperButtonDto makerPaperButtonDto){
        ApiResult result = examMakePaperService.makePaperButton(makerPaperButtonDto);
        return result;
    }

}
