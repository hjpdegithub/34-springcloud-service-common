package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuth;
import com.springboot.boot.modules.admin.entity.MpAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAuthMapper {
    long countByExample(MpAuthExample example);

    int deleteByExample(MpAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAuth record);

    int insertSelective(MpAuth record);

    List<MpAuth> selectByExample(MpAuthExample example);

    MpAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAuth record, @Param("example") MpAuthExample example);

    int updateByExample(@Param("record") MpAuth record, @Param("example") MpAuthExample example);

    int updateByPrimaryKeySelective(MpAuth record);

    int updateByPrimaryKey(MpAuth record);
}