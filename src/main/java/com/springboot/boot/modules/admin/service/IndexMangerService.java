package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.Index.IndexManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.dto.file.CommonupTypeDto;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface IndexMangerService {
    ApiResult addOrUpdate(@RequestBody IndexManageDto IndexManageDto);
    ApiResult list(@RequestBody IndexManageDto indexManageDto);
    ApiResult delete(@RequestBody IndexManageDto indexManageDto);

}