package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.vo.test.UsertestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestCountsMapper {
    Long testUsersCount();
    Long testPaperCount();
    Long titleCount();
    Long testTimes();
    List<UsertestVo> myTestList(@Param("userId") long userId);

}