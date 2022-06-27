package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.AuthClassifyService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(value="/searchByAuthdirectionName")
    public ApiResult searchByAuthdirectionName(@RequestBody MpAuthDto dto){
        log.info("分类的新增以及修改----------参数对象输出{}", JSONObject.toJSON(dto));
        //判断一级分类名称是否相同

        return ApiResult.success(authClassifyService.searchByAuthdirectionName(dto));
    }



    @ApiOperation(value = "查看二级分类信息", notes="该接口根据一级分类id查询包括二级分类信息")
    @PostMapping(value="/searchSecondClassify")
    public ApiResult searchSecondClassify(@RequestBody ClassifySearchAllDto dto){
        log.info("二级分类传参输出参数====================={}",JSONObject.toJSON(dto));
        PageInfo<MpSecondClassify>  allVo =  classifyService.searchSecondClassify(dto);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "查看一级分类信息", notes="该接口包括一级分类信息")
    @GetMapping(value="/searchFristClassify")
    public ApiResult searchFristClassify(){
        List<MpFirstClassify>  allVo =  classifyService.searchFristClassify();
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "查看所有分类信息2.0认证版本通用", notes="该接口包括一二级分类信息")
    @GetMapping(value="/searchClassifyAll")
    public ApiResult searchClassifyAll(@RequestParam("classifyType")  Integer classifyType){
        log.info("查看所有分类信息==========请求参数输出========"+classifyType);
        List<ClassifyAllVo>  allVo =  classifyService.searchClassifyAll(classifyType);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "根据一级分类查询分类信息", notes="该接口包括一级分类信息")
    @GetMapping(value="/searchFirstClassify")
    public ApiResult searchFirstClassify(@RequestParam("id")  Long id){
        log.info("根据一级分类查询分类信息:{}",id);
        MpFirstClassify allVo =  classifyService.searchFirstClassify(id);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "根据二级分类id查询二级分类信息", notes="该接口包括二级分类信息")
    @GetMapping(value="/searchSecondClassifyById")
    public ApiResult searchSecondClassifyById(@RequestParam("id")  Long id){
        log.info("根据二级分类查询分类信息:{}",id);
        MpSecondClassify allVo =  classifyService.searchSecondClassifyById(id);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "二级分类删除操作：批量", notes="该接口只删除二级分类")
    @PostMapping(value="/deleteByIds")
    public ApiResult deleteByids(@RequestBody ClassifyDelSecondDto dto){
        log.info("分类的删除----------参数对象输出{}",JSONObject.toJSON(dto));
        ApiResult result = classifyService.deleteByids(dto);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "一级分类删除操作", notes="该接口只删除一级分类")
    @PostMapping(value="/deleteById")
    public ApiResult deleteById( @RequestBody ClassifyDelFirstDto dto){
        log.info("分类的删除----------参数对象输出{}",JSONObject.toJSON(dto));
        ApiResult result = classifyService.deleteById(dto);
        return ApiResult.success(result);
    }
}
