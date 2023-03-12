package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.dto.file.CommonupTypeDto;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface BannerMangerService {
    ApiResult addOrUpdate(@RequestBody BannerManageDto bannerManageDto);
    ApiResult list(@RequestBody BannerManageDto bannerManageDto);
    ApiResult listforShow(@RequestBody BannerManageNoPageDto bannerManageDto) ;
    ApiResult delete(@RequestBody CommonupTypeDto dto);
    ApiResult upType(@RequestBody CommonupTypeDto dto);
}