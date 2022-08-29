package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpMakePaper;
import com.springboot.boot.modules.admin.entity.MpMakePaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpMakePaperMapper {
    long countByExample(MpMakePaperExample example);

    int deleteByExample(MpMakePaperExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpMakePaper record);

    int insertSelective(MpMakePaper record);

    List<MpMakePaper> selectByExample(MpMakePaperExample example);

    MpMakePaper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpMakePaper record, @Param("example") MpMakePaperExample example);

    int updateByExample(@Param("record") MpMakePaper record, @Param("example") MpMakePaperExample example);

    int updateByPrimaryKeySelective(MpMakePaper record);

    int updateByPrimaryKey(MpMakePaper record);
}