package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.file.BannerFileDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.dto.file.CommonupTypeDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentInfo;
import com.springboot.boot.modules.admin.entity.MpBannerManage;
import com.springboot.boot.modules.admin.entity.MpBannerManageExample;
import com.springboot.boot.modules.admin.mapper.AttachmentInfoMapper;
import com.springboot.boot.modules.admin.mapper.MpAttachmentInfoMapper;
import com.springboot.boot.modules.admin.mapper.MpBannerManageMapper;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.BannerMangerService;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AppClassifyController
 * @Description TODO 前端分页展示
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/app/banner")
@Slf4j
@Api(tags = "1.4.1.1", description = "前端banner图【侯建鹏】")
@CrossOrigin
public class BannerController {
    @Autowired
    private BannerMangerService bannerMangerService;
    /**
     * 自助列表2.0版本加入认证筛选条件
     *
     * @return
     */
    @ApiOperation(value = "addOrUpdate", notes = "banner图的上传或修改")
    @PostMapping(value = "/addOrUpdate")
    public ApiResult addOrUpdate(@RequestBody BannerManageDto bannerManageDto) {
        return bannerMangerService.addOrUpdate(bannerManageDto);
    }
    @ApiOperation(value = "list", notes = "banner图列表查询")
    @PostMapping(value = "/list")
    public ApiResult list(@RequestBody BannerManageDto bannerManageDto) {
        return bannerMangerService.list(bannerManageDto);
    }
    @ApiOperation(value = "listforShow", notes = "banner图列表查询")
    @PostMapping(value = "/listforShow")
    public ApiResult listforShow(@RequestBody BannerManageNoPageDto bannerManageDto) {
        return bannerMangerService.listforShow(bannerManageDto);
    }
    @ApiOperation(value = "delete", notes = "banner图删除")
    @PostMapping(value = "/delete")
    public ApiResult delete(@RequestBody CommonupTypeDto dto) {
        return bannerMangerService.delete(dto);
    }
    @ApiOperation(value = "upType", notes = "banner图删除")
    @PostMapping(value = "/upType")
    public ApiResult upType(@RequestBody CommonupTypeDto dto) {
        return bannerMangerService.upType(dto);
    }


}