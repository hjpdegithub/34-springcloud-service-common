package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpReading;
import com.springboot.boot.modules.admin.entity.MpReadingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpReadingMapper {
    long countByExample(MpReadingExample example);

    int deleteByExample(MpReadingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpReading record);

    int insertSelective(MpReading record);

    List<MpReading> selectByExample(MpReadingExample example);

    MpReading selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpReading record, @Param("example") MpReadingExample example);

    int updateByExample(@Param("record") MpReading record, @Param("example") MpReadingExample example);

    int updateByPrimaryKeySelective(MpReading record);

    int updateByPrimaryKey(MpReading record);
}