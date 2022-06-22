package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.AppClassifyService;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppClassifyController
 * @Description TODO 前端分页展示
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/app/classify")
@Slf4j
@Api(tags = "1.4", description = "前端分类展示【周京昊】")
@CrossOrigin
public class AppClassifyController {


    @Autowired
    private AppClassifyService appClassifyService;

    /**
     * 自助列表2.0版本加入认证筛选条件
     * @return
     */
    @ApiOperation(value = "自助学习列表", notes="自助学习列表")
    @GetMapping(value="/searchStudy")
    public ApiResult searchStudy(){
        List<SearchStudyVo> allVo =  appClassifyService.searchStudy();
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "定制课程展示页面接口", notes="包括分类信息的展示和相关课程的计算")
    @GetMapping(value="/classifyShow")
    public ApiResult classifyShow(@RequestParam("id") Long id){
        log.info("定制课程展示页面接口============"+id);
       ClassifyTypeShowStudyVo allVo =  appClassifyService.classifyShow(id);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "根据一级分类id查询二级分类信息", notes="根据一级分类id查询二级分类信息")
    @GetMapping(value="/searchByFirstId")
    public ApiResult searchByFirstId(@RequestParam("id") Long id){
        log.info("定制课程展示页面接口============"+id);
        List<MpSecondClassify> allVo =  appClassifyService.searchByFirstId(id);
        return ApiResult.success(allVo);
    }

}
