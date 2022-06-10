package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurriculumAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CurriculumController
 * @Description TODO 课程管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/curriculum")
@Slf4j
@Api(tags = "1.2", description = "课程管理【周京昊】")
@CrossOrigin
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @ApiOperation(value = "课程的新增以及修改", notes="该接口根据课程的主键id判断编辑传入")
    @PostMapping(value="/addOrUpdate")
    public ApiResult addOrUpdate(@RequestBody CurriculumAddOrUpdateDto dto){

        log.info("课程的新增以及修改----------参数对象输出{}", JSONObject.toJSON(dto));
        ApiResult result = curriculumService.addOrUpdate(dto);
        return result;
    }

    @ApiOperation(value = "课程查询接口", notes="课程下的所有信息")
    @PostMapping(value="/search")
    public ApiResult search(@RequestBody SearchCurriculumDto dto){
        log.info("课程查询参数输出----------参数对象输出---{}",JSONObject.toJSON(dto));
        PageInfo<CurriculumVo> result = curriculumService.search(dto);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "通过id查询课程接口", notes="课程下的所有信息")
    @GetMapping(value="/searchById")
    public ApiResult searchById(@RequestParam Long id){
        log.info("课程查询参数输出----------参数对象输出---{}",JSONObject.toJSON(id));
        MpCurriculum result = curriculumService.searchById(id);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "课程的删除", notes="课程的删除")
    @PostMapping(value="/delete")
    public ApiResult delete(@RequestBody ClassifyDelSecondDto dto){
        log.info("课程的删除----------参数对象输出{}",dto);
        ApiResult result = curriculumService.delete(dto);
        return result;
    }
}
