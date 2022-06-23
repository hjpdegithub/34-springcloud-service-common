package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AppClassifyController
 * @Description TODO 认证管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/app/auth")
@Slf4j
@Api(tags = "1.5.9", description = "认证管理【侯建鹏】")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;



    //接口部门/mp/department
    //单位查询/mp/unit

    @ApiOperation(value = "认证信息的新增以及修改", notes = "认证信息的新增以及修改")
    @PostMapping(value = "/addOrUpdate")
    public ApiResult addOrUpdate(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "authDescr", required = false) String authDescr,
            @RequestParam(value = "directionId", required = false) Long directionId,
            @RequestParam(value = "domainId", required = false) Long domainId,
            @RequestParam(value = "unitId", required = false) Long unitId,
            @RequestParam(value = "authLevel", required = false) Integer authLevel,
            @RequestParam(value = "examId", required = false) Long examId,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "authFirstClassifyId", required = false) Long authFirstClassifyId,
            @RequestParam(value = "authSencondClassifyId", required = false) Long authSencondClassifyId,
            @RequestParam(value = "certificateTime", required = false) Long certificateTime,
            @RequestParam(value = "certificateType", required = false) Integer certificateType,
            @RequestParam(value = "endTime", required = false) Long endTime,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam("file") MultipartFile file) {

        MpAuthDto dto = new MpAuthDto();
        dto.setId(id);
        dto.setName(name);
        dto.setAuthDescr(authDescr);
        dto.setDirectionId(directionId);
        dto.setDomainId(domainId);
        dto.setUnitId(unitId);
        dto.setAuthLevel(authLevel);
        dto.setExamId(examId);
        dto.setDepartmentId(departmentId);
        dto.setAuthFirstClassifyId(authFirstClassifyId);
        dto.setAuthSencondClassifyId(authSencondClassifyId);
        dto.setCertificateTime(new Date(certificateTime));
        dto.setCertificateType(certificateType);
        dto.setEndTime(new Date(endTime));
        dto.setUserId(userId);
        dto.setCreateTime(new Date());
        log.info("认证信息的新增以及修改----------", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同
        ApiResult result = authService.addOrUpdate(dto, file);
        return result;
    }


    //列表查询



    @ApiOperation(value = "认证信息列表查询", notes = "认证信息列表查询")
    @PostMapping(value = "/search")
    public ApiResult search(@RequestBody MpAuthDto dto
          ) {

        log.info("认证信息列表查询开始----------", JSONObject.toJSON(dto));

        return  ApiResult.success(authService.search(dto ));

    }









}
