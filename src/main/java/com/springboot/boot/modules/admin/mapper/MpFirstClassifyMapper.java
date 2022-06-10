package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpFirstClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpFirstClassifyMapper {
    long countByExample(MpFirstClassifyExample example);

    int deleteByExample(MpFirstClassifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpFirstClassify record);

    int insertSelective(MpFirstClassify record);

    List<MpFirstClassify> selectByExample(MpFirstClassifyExample example);

    MpFirstClassify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpFirstClassify record, @Param("example") MpFirstClassifyExample example);

    int updateByExample(@Param("record") MpFirstClassify record, @Param("example") MpFirstClassifyExample example);

    int updateByPrimaryKeySelective(MpFirstClassify record);

    int updateByPrimaryKey(MpFirstClassify record);
}