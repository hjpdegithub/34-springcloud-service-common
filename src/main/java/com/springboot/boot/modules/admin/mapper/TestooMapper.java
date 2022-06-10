package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.Testoo;
import com.springboot.boot.modules.admin.entity.TestooExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestooMapper {
    long countByExample(TestooExample example);

    int deleteByExample(TestooExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Testoo record);

    int insertSelective(Testoo record);

    List<Testoo> selectByExample(TestooExample example);

    Testoo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Testoo record, @Param("example") TestooExample example);

    int updateByExample(@Param("record") Testoo record, @Param("example") TestooExample example);

    int updateByPrimaryKeySelective(Testoo record);

    int updateByPrimaryKey(Testoo record);
}