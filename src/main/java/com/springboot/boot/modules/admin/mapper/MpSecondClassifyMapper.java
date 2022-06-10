package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassifyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MpSecondClassifyMapper {
    long countByExample(MpSecondClassifyExample example);

    int deleteByExample(MpSecondClassifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpSecondClassify record);

    int insertSelective(MpSecondClassify record);

    List<MpSecondClassify> selectByExample(MpSecondClassifyExample example);

    MpSecondClassify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpSecondClassify record, @Param("example") MpSecondClassifyExample example);

    int updateByExample(@Param("record") MpSecondClassify record, @Param("example") MpSecondClassifyExample example);

    int updateByPrimaryKeySelective(MpSecondClassify record);

    int updateByPrimaryKey(MpSecondClassify record);
}