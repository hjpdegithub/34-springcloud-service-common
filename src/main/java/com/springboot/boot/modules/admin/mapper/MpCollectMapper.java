package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpCollect;
import com.springboot.boot.modules.admin.entity.MpCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpCollectMapper {
    long countByExample(MpCollectExample example);

    int deleteByExample(MpCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpCollect record);

    int insertSelective(MpCollect record);

    List<MpCollect> selectByExample(MpCollectExample example);

    MpCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpCollect record, @Param("example") MpCollectExample example);

    int updateByExample(@Param("record") MpCollect record, @Param("example") MpCollectExample example);

    int updateByPrimaryKeySelective(MpCollect record);

    int updateByPrimaryKey(MpCollect record);
}