package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.service.DepartmentService;
import com.springboot.boot.modules.admin.service.UnitService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DepartmentController
 * @Description TODO 部门管理
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:10
 * @Version 2.0
 **/
@RestController
@RequestMapping("/mp/department")
@Slf4j
@Api(tags = "2.2", description = "部门管理【周京昊】")
@CrossOrigin
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;
    @ApiOperation(value = "部门管理管理", notes="")
    @PostMapping(value="/search")
    public ApiResult search(){
        ApiResult result = departmentService.search();
        return result;

    }
}
