package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUserAuthentication;
import com.springboot.boot.modules.admin.entity.MpUserAuthenticationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUserAuthenticationMapper {
    long countByExample(MpUserAuthenticationExample example);

    int deleteByExample(MpUserAuthenticationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUserAuthentication record);

    int insertSelective(MpUserAuthentication record);

    List<MpUserAuthentication> selectByExample(MpUserAuthenticationExample example);

    MpUserAuthentication selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUserAuthentication record, @Param("example") MpUserAuthenticationExample example);

    int updateByExample(@Param("record") MpUserAuthentication record, @Param("example") MpUserAuthenticationExample example);

    int updateByPrimaryKeySelective(MpUserAuthentication record);

    int updateByPrimaryKey(MpUserAuthentication record);
}