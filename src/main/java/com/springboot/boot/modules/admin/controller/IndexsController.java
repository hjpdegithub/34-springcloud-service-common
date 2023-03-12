package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.Index.IndexManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.dto.file.CommonupTypeDto;
import com.springboot.boot.modules.admin.service.BannerMangerService;
import com.springboot.boot.modules.admin.service.IndexMangerService;
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
@RequestMapping("/mp/app/indexs")
@Slf4j
@Api(tags = "1.4.1.2", description = "index指标图【侯建鹏】")
@CrossOrigin
public class IndexsController {
    @Autowired
    private IndexMangerService indexMangerService;
    /**
     * 自助列表2.0版本加入认证筛选条件
     *
     * @return
     */
    @ApiOperation(value = "addOrUpdate", notes = "指标上传或修改")
    @PostMapping(value = "/addOrUpdate")
    public ApiResult addOrUpdate(@RequestBody IndexManageDto indexManageDto) {
        return indexMangerService.addOrUpdate(indexManageDto);
    }
    @ApiOperation(value = "list", notes = "指标图列表查询")
    @PostMapping(value = "/list")
    public ApiResult list(@RequestBody IndexManageDto indexManageDto) {
        return indexMangerService.list(indexManageDto);
    }
    @ApiOperation(value = "delete", notes = "指标图删除")
    @PostMapping(value = "/delete")
    public ApiResult delete(@RequestBody IndexManageDto dto) {
        return indexMangerService.delete(dto);
    }
}