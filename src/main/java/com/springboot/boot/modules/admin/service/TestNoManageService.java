package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.test.MpUserAuthenticationDto;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.utils.ApiResult;

public interface TestNoManageService {


    ApiResult testNoApply(MpUserAuthenticationDto dto);


    ApiResult queryTestNoByPhoneNo(MpUserAuthenticationDto dto);


    MpUserAuthenticationVo TestNoVerifys(MpUserAuthenticationDto dto);

    Integer TestNoVerifysEdit(MpUserAuthenticationDto dto);




}
