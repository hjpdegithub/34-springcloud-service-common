package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAuthCertificase;
import com.springboot.boot.modules.admin.entity.MpAuthCertificaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAuthCertificaseMapper {
    long countByExample(MpAuthCertificaseExample example);

    int deleteByExample(MpAuthCertificaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAuthCertificase record);

    int insertSelective(MpAuthCertificase record);

    List<MpAuthCertificase> selectByExample(MpAuthCertificaseExample example);

    MpAuthCertificase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAuthCertificase record, @Param("example") MpAuthCertificaseExample example);

    int updateByExample(@Param("record") MpAuthCertificase record, @Param("example") MpAuthCertificaseExample example);

    int updateByPrimaryKeySelective(MpAuthCertificase record);

    int updateByPrimaryKey(MpAuthCertificase record);
}