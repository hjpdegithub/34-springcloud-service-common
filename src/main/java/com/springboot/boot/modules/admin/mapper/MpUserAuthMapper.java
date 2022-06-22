package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUserAuth;
import com.springboot.boot.modules.admin.entity.MpUserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUserAuthMapper {
    long countByExample(MpUserAuthExample example);

    int deleteByExample(MpUserAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUserAuth record);

    int insertSelective(MpUserAuth record);

    List<MpUserAuth> selectByExample(MpUserAuthExample example);

    MpUserAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUserAuth record, @Param("example") MpUserAuthExample example);

    int updateByExample(@Param("record") MpUserAuth record, @Param("example") MpUserAuthExample example);

    int updateByPrimaryKeySelective(MpUserAuth record);

    int updateByPrimaryKey(MpUserAuth record);
}