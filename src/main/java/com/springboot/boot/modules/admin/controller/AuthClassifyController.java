package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.service.AuthClassifyService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AuthClassifyController
 * @Description TODO 分类管理
 * @Author jhzhou
 * @Date 2022/3/11 0011 13:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/authClassify")
@Slf4j
@Api(tags = "10.3", description = "分类管理【侯建鹏】")
@CrossOrigin
public class AuthClassifyController {

    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private AuthClassifyService  authClassifyService;

    @ApiOperation(value = "认证分类的新增以及修改", notes="该接口认证分类主键id判断新增不传，修改需要传入")
    @PostMapping(value="/addOrUpdate")
    public ApiResult addOrUpdate( @RequestBody MpAuthDirectionAddOrUpdateDto dto){
        log.info("分类的新增以及修改----------参数对象输出{}", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同
        ApiResult result = authClassifyService.addOrUpdate(dto);
        return result;
    }

    @ApiOperation(value = "根据名称查询认证方向列表", notes="根据名称查询认证方向列表，如果名称为空则查询全部，包含认证领域列表")
    @PostMapping(value="/searchByNameOrId")
    public ApiResult searchByAuthdirectionName(@RequestBody MpNameIdsDto dto){
        log.info("分类的新增以及修改----------参数对象输出{}", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同
        return ApiResult.success(authClassifyService.searchByAuthdirectionName(dto));
    }


    @ApiOperation(value = "根据ids删除认证方向以及认证领域", notes="根据ids删除认证方向以及认证领域")
    @PostMapping(value="/deleteByIds")
    public ApiResult deleteByIds(@RequestBody MpNameIdsDto dto){
        log.info("分类的新增以及修改----------参数对象输出{}", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同
        return ApiResult.success(authClassifyService.deleteByIds(dto));
    }





}
