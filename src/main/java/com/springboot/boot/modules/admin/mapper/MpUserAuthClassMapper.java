package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUserAuthClass;
import com.springboot.boot.modules.admin.entity.MpUserAuthClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUserAuthClassMapper {
    long countByExample(MpUserAuthClassExample example);

    int deleteByExample(MpUserAuthClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUserAuthClass record);

    int insertSelective(MpUserAuthClass record);

    List<MpUserAuthClass> selectByExample(MpUserAuthClassExample example);

    MpUserAuthClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUserAuthClass record, @Param("example") MpUserAuthClassExample example);

    int updateByExample(@Param("record") MpUserAuthClass record, @Param("example") MpUserAuthClassExample example);

    int updateByPrimaryKeySelective(MpUserAuthClass record);

    int updateByPrimaryKey(MpUserAuthClass record);
}