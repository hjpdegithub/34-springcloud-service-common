package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuthDirection;
import com.springboot.boot.modules.admin.entity.MpAuthDirectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAuthDirectionMapper {
    long countByExample(MpAuthDirectionExample example);

    int deleteByExample(MpAuthDirectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAuthDirection record);

    int insertSelective(MpAuthDirection record);

    List<MpAuthDirection> selectByExample(MpAuthDirectionExample example);

    MpAuthDirection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAuthDirection record, @Param("example") MpAuthDirectionExample example);

    int updateByExample(@Param("record") MpAuthDirection record, @Param("example") MpAuthDirectionExample example);

    int updateByPrimaryKeySelective(MpAuthDirection record);

    int updateByPrimaryKey(MpAuthDirection record);
}