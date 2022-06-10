package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpCurthu;
import com.springboot.boot.modules.admin.entity.MpCurthuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpCurthuMapper {
    long countByExample(MpCurthuExample example);

    int deleteByExample(MpCurthuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpCurthu record);

    int insertSelective(MpCurthu record);

    List<MpCurthu> selectByExample(MpCurthuExample example);

    MpCurthu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpCurthu record, @Param("example") MpCurthuExample example);

    int updateByExample(@Param("record") MpCurthu record, @Param("example") MpCurthuExample example);

    int updateByPrimaryKeySelective(MpCurthu record);

    int updateByPrimaryKey(MpCurthu record);
}