package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.entity.MpAuth;
import com.springboot.boot.modules.admin.entity.MpAuthExample;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpAuthHMapper {

    List<MpAuthHVo> selectAllMpAuths(@Param("dto") MpAuthDto dto);

    Long selectFileId(@Param("id") Long id);

    List<MpUserAuthenticationVo> certifiQuery(@Param("dto") MpNameIdsDto dto );

}