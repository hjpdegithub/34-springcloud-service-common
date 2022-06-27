package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuth;
import com.springboot.boot.modules.admin.entity.MpAuthExample;
import com.springboot.boot.modules.admin.vo.auth.AuthAndUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpAuthBusinessMapper {

    List<AuthAndUserVo> authBanner();

}