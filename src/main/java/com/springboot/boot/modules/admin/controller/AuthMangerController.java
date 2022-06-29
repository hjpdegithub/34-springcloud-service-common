package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.service.AuthManageService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @ClassName AppClassifyController
 * @Description TODO 认证管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 * =======
 * <p>
 * import java.util.List;
 * <p>
 * /**
 * @ClassName AuthController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/22 0022 14:33
 * @Version 2.0
 **/
@RestController
@RequestMapping("/mp/app/authManage")
@Slf4j
@Api(tags = "10.2", description = "认证管理【侯建鹏】")

@CrossOrigin
public class AuthMangerController {

    @Autowired
    private AuthManageService authService;
    //接口部门/mp/department
    //单位查询/mp/unit
    //课程分类searchClassifyAll包括一级分类2级分类
    //该接口根据一级分类id查询包括二级分类信息")


    @ApiOperation(value = "认证信息的新增以及修改", notes = "认证信息的新增以及修改")
    @PostMapping(value = "/addOrUpdate")
    public ApiResult addOrUpdate(

            @RequestBody  MpAuthDto dto
            ) {

        log.info("认证信息的新增以及修改----------", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同
        ApiResult result = authService.addOrUpdate(dto);
        return result;
    }


    //列表查询
    @ApiOperation(value = "认证信息列表查询", notes = "认证信息列表查询")
    @PostMapping(value = "/search")
    public ApiResult search(@RequestBody MpAuthDto dto
    ) {
        log.info("认证信息列表查询开始----------", JSONObject.toJSON(dto));
        return ApiResult.success(authService.search(dto));
    }




    //列表查询
    @ApiOperation(value = "认证信息详情查询", notes = "认证信息详情查询")
    @PostMapping(value = "/searchById")
    public ApiResult searchById(@RequestBody MpAuthDto dto
    ) {
        log.info("认证信息详情查询----------", JSONObject.toJSON(dto));
        return ApiResult.success(authService.searchById(dto));
    }


    //上下线
    @ApiOperation(value = "认证信息列表查询", notes = "认证信息列表查询")
    @PostMapping(value = "/onOffLine")
    public ApiResult onOffLine(@RequestBody MpAuthDto dto
    ) {
        log.info("认证信息上下线----------", JSONObject.toJSON(dto));
        return ApiResult.success(authService.onOffLine(dto));
    }


    @ApiOperation(value = "认证信息列表查询", notes = "认证信息列表查询")
    @PostMapping(value = "/deleteBatch")
    public ApiResult deleteBatch(@RequestBody MpNameIdsDto dto
    ) {
        log.info("认证批量删除----------", JSONObject.toJSON(dto));
        return ApiResult.success(authService.deleteBatch(dto));
    }


}
