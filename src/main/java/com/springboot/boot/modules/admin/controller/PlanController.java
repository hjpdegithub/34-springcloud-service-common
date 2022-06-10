package com.springboot.boot.modules.admin.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.NewTable;
import com.springboot.boot.modules.admin.service.TestService;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.RedisCacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/plan")
@Slf4j
@Api(tags = "1.0", description = "test【周京昊】")
public class PlanController {

    @Resource
    private TestService service;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @ApiOperation(value = "test", notes="tets")
    @PostMapping(value="/list")
    public ApiResult list(@Valid @RequestBody ClassifySearchAllDto dto){
        PageInfo<NewTable> newTableList = service.searchAll(dto);

        return ApiResult.success(newTableList);
    }

    @ApiOperation(value="test1")
    @GetMapping(value="/test")
    public ApiResult test(){

        List<Object> objects = new ArrayList<>();
        objects.add("asd");
        redisCacheUtil.setCacheList("user",objects);
        return ApiResult.success();
    }




}