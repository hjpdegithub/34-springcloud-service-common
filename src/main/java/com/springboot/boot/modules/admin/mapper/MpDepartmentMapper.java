package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpDepartment;
import com.springboot.boot.modules.admin.entity.MpDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpDepartmentMapper {
    long countByExample(MpDepartmentExample example);

    int deleteByExample(MpDepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpDepartment record);

    int insertSelective(MpDepartment record);

    List<MpDepartment> selectByExample(MpDepartmentExample example);

    MpDepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpDepartment record, @Param("example") MpDepartmentExample example);

    int updateByExample(@Param("record") MpDepartment record, @Param("example") MpDepartmentExample example);

    int updateByPrimaryKeySelective(MpDepartment record);

    int updateByPrimaryKey(MpDepartment record);
}