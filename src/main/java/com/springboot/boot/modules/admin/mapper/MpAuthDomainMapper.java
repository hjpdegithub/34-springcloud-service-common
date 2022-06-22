package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuthDomain;
import com.springboot.boot.modules.admin.entity.MpAuthDomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAuthDomainMapper {
    long countByExample(MpAuthDomainExample example);

    int deleteByExample(MpAuthDomainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAuthDomain record);

    int insertSelective(MpAuthDomain record);

    List<MpAuthDomain> selectByExample(MpAuthDomainExample example);

    MpAuthDomain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAuthDomain record, @Param("example") MpAuthDomainExample example);

    int updateByExample(@Param("record") MpAuthDomain record, @Param("example") MpAuthDomainExample example);

    int updateByPrimaryKeySelective(MpAuthDomain record);

    int updateByPrimaryKey(MpAuthDomain record);
}