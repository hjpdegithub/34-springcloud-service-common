package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpErrorQuestion;
import com.springboot.boot.modules.admin.entity.MpErrorQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpErrorQuestionMapper {
    long countByExample(MpErrorQuestionExample example);

    int deleteByExample(MpErrorQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpErrorQuestion record);

    int insertSelective(MpErrorQuestion record);

    List<MpErrorQuestion> selectByExample(MpErrorQuestionExample example);

    MpErrorQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpErrorQuestion record, @Param("example") MpErrorQuestionExample example);

    int updateByExample(@Param("record") MpErrorQuestion record, @Param("example") MpErrorQuestionExample example);

    int updateByPrimaryKeySelective(MpErrorQuestion record);

    int updateByPrimaryKey(MpErrorQuestion record);
}