package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.TestUserDto;

import com.springboot.boot.modules.admin.dto.test.TestResultDto;
import com.springboot.boot.modules.admin.vo.test.MpExamAchievementMultiVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.modules.admin.vo.test.TestResultPageDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpExamAchievementTwoMapper {
    public List<MpExamAchievementMultiVo> testResultQuery(@Param("dto") TestResultPageDto dto) ;
    public List<MpExamAchievementMultiVo> testResultNoPageQuery(@Param("dto") TestResultDto dto) ;
    public List<MpUserAuthenticationVo> testUserList(@Param("dto") TestUserDto  dto);




}