package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.service.TestCountService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClassifyController
 * @Description TODO 分类管理
 * @Author jhzhou
 * @Date 2022/3/11 0011 13:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/TestCount")
@Slf4j
@Api(tags = "1.5.6", description = "在线考试考试统计【侯建鹏】")
@CrossOrigin
public class TestCountController {
    @Autowired
    private TestCountService testCountService;
    @ApiOperation(value = "考试数量统计", notes="考试数量统计")
    @PostMapping(value="/testCounts")
    public ApiResult testCounts(){
        log.info("考试数量统计----------{}" );
       return ApiResult.success(testCountService.testCounts()) ;
    }
    @ApiOperation(value = "我报名的考试列表", notes="我报名的考试列表")
    @PostMapping(value="/myTestList")
    public ApiResult myTestList(@RequestBody CommonAllDto dto){
        log.info("考试数量统计----------{}" );
        //判断一级分类名称是否相同
        return ApiResult.success(testCountService.myTestList(dto)) ;
    }
    @ApiOperation(value = "问卷调查列表", notes="问卷调查列表")
    @PostMapping(value="/questionList")
    public ApiResult questionList(){
        log.info("问卷调查列表统计----------{}" );
        //判断一级分类名称是否相同
        return ApiResult.success(testCountService.questionList()) ;
    }
    @ApiOperation(value = "考试列表", notes="考试列表")
    @PostMapping(value="/testList")
    public ApiResult testList(){
        log.info("考试列表----------{}" );
        //判断一级分类名称是否相同
        return ApiResult.success(testCountService.testList()) ;
    }
}
