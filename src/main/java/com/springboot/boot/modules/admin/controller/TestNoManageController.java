package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.test.MpUserAuthenticationDto;

import com.springboot.boot.modules.admin.service.TestNoManageService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AppClassifyController
 * @Description TODO 前端分页展示
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/test")
@Slf4j
@Api(tags = "1.5.5", description = "考试认证【侯建鹏】")
@CrossOrigin
public class TestNoManageController {
    @Autowired
    private TestNoManageService testNoManageService;
    @ApiOperation(value = "考试编号申请", notes = "考试编号申请")
    @PostMapping(value = "/testNoApply")
    public ApiResult testNoApply(@RequestBody MpUserAuthenticationDto dto) {
        return ApiResult.success(
                testNoManageService.testNoApply(dto));
    }
    @ApiOperation(value = "根据手机号查询编号", notes = "根据手机号查询编号")
    @PostMapping(value = "/queryTestNoByPhoneNo")
//    {
//        "phone": "123"
//    }
    public ApiResult queryTestNoByPhoneNo(@RequestBody MpUserAuthenticationDto dto) {
        return ApiResult.success(testNoManageService.queryTestNoByPhoneNo(dto));
    }
    @ApiOperation(value = "验证", notes = "验证")
    @PostMapping(value = "/TestNoVerifys")
    public ApiResult TestNoVerifys(@RequestBody MpUserAuthenticationDto dto) {
        return ApiResult.success(testNoManageService.TestNoVerifys(dto));
    }


    @ApiOperation(value = "考试编号信息更新", notes = "考试编号信息更新")
    @PostMapping(value = "/TestNoVerifysEdit")
    public ApiResult TestNoVerifysEdit(@RequestBody MpUserAuthenticationDto dto) {
        return ApiResult.success(testNoManageService.TestNoVerifys(dto));
    }





}
