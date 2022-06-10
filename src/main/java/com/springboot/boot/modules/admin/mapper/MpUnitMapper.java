package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUnit;
import com.springboot.boot.modules.admin.entity.MpUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUnitMapper {
    long countByExample(MpUnitExample example);

    int deleteByExample(MpUnitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUnit record);

    int insertSelective(MpUnit record);

    List<MpUnit> selectByExample(MpUnitExample example);

    MpUnit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUnit record, @Param("example") MpUnitExample example);

    int updateByExample(@Param("record") MpUnit record, @Param("example") MpUnitExample example);

    int updateByPrimaryKeySelective(MpUnit record);

    int updateByPrimaryKey(MpUnit record);
}