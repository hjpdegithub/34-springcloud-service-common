package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.service.ExcTemManService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mp/file")
@Slf4j
@Api(tags = "2.0", description = "Excel模板管理接口【侯建鹏】")
@CrossOrigin
public class ExcTempManController {


    @Autowired
    private ExcTemManService excTemManService;

    @CrossOrigin
    @PostMapping(value = "/exelTemFileSelectById")
    @ApiOperation(value = "按照id查询课件详细信息")
    public ApiResult attachmentFileSelectById(
            @RequestBody CommonAllDto commonAllDto) {
        return    ApiResult.success(excTemManService.attachmentFileSelectById(commonAllDto));
    }






}
