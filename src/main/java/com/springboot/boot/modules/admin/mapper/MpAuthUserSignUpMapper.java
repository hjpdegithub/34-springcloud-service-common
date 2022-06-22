package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuthUserSignUp;
import com.springboot.boot.modules.admin.entity.MpAuthUserSignUpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAuthUserSignUpMapper {
    long countByExample(MpAuthUserSignUpExample example);

    int deleteByExample(MpAuthUserSignUpExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAuthUserSignUp record);

    int insertSelective(MpAuthUserSignUp record);

    List<MpAuthUserSignUp> selectByExample(MpAuthUserSignUpExample example);

    MpAuthUserSignUp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAuthUserSignUp record, @Param("example") MpAuthUserSignUpExample example);

    int updateByExample(@Param("record") MpAuthUserSignUp record, @Param("example") MpAuthUserSignUpExample example);

    int updateByPrimaryKeySelective(MpAuthUserSignUp record);

    int updateByPrimaryKey(MpAuthUserSignUp record);
}