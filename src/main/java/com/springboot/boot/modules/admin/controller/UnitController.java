package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.examination.ExaminationAddOrUpdateDto;
import com.springboot.boot.modules.admin.service.UnitService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UnitController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:01
 * @Version 2.0
 **/
@RestController
@RequestMapping("/mp/unit")
@Slf4j
@Api(tags = "2.1", description = "单位管理【周京昊】")
@CrossOrigin
public class UnitController {

    @Resource
    private UnitService unitService;
    @ApiOperation(value = "单位查询接口", notes="")
    @PostMapping(value="/search")
    public ApiResult search(){
        ApiResult result = unitService.search();
        return result;

    }
}
