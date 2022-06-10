package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpSignUp;
import com.springboot.boot.modules.admin.entity.MpSignUpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpSignUpMapper {
    long countByExample(MpSignUpExample example);

    int deleteByExample(MpSignUpExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpSignUp record);

    int insertSelective(MpSignUp record);

    List<MpSignUp> selectByExample(MpSignUpExample example);

    MpSignUp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpSignUp record, @Param("example") MpSignUpExample example);

    int updateByExample(@Param("record") MpSignUp record, @Param("example") MpSignUpExample example);

    int updateByPrimaryKeySelective(MpSignUp record);

    int updateByPrimaryKey(MpSignUp record);
}