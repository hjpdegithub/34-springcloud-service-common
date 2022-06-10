package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.NewTable;
import com.springboot.boot.modules.admin.entity.NewTableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
public interface NewTableMapper {
    long countByExample(NewTableExample example);

    int deleteByExample(NewTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NewTable record);

    int insertSelective(NewTable record);

    List<NewTable> selectByExample(NewTableExample example);

    NewTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NewTable record, @Param("example") NewTableExample example);

    int updateByExample(@Param("record") NewTable record, @Param("example") NewTableExample example);

    int updateByPrimaryKeySelective(NewTable record);

    int updateByPrimaryKey(NewTable record);
}