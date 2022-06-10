package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.service.CurEliteService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CurriculumController
 * @Description TODO 课程管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/elite")
@Slf4j
@Api(tags = "1.2.1", description = "精品课程查询【侯建鹏】")
@CrossOrigin
public class CurEliteController {


    @Autowired
    private CurEliteService curEliteService;

    @ApiOperation(value = "1精品课程查询", notes = "1精品课程查询")
    @PostMapping(value = "/curEliteSelect")
    public ApiResult curEliteSelect() {
        return ApiResult.success(curEliteService.curEliteSelect());
    }

    @ApiOperation(value = "2课程详情查询", notes = "1精品课程查询")
    @PostMapping(value = "/curSecondDetailSelectById")
    public ApiResult curSecondDetailSelectById(CommonAllDto dto) {
        return ApiResult.success(curEliteService.curSecondDetailSelectById(dto));
    }




}
