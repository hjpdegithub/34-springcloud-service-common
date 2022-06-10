package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpOption;
import com.springboot.boot.modules.admin.entity.MpOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpOptionMapper {
    long countByExample(MpOptionExample example);

    int deleteByExample(MpOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpOption record);

    int insertSelective(MpOption record);

    List<MpOption> selectByExample(MpOptionExample example);

    MpOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpOption record, @Param("example") MpOptionExample example);

    int updateByExample(@Param("record") MpOption record, @Param("example") MpOptionExample example);

    int updateByPrimaryKeySelective(MpOption record);

    int updateByPrimaryKey(MpOption record);
}