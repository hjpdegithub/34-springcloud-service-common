package com.springboot.boot.modules.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.NewTable;
import com.springboot.boot.modules.admin.entity.NewTableExample;
import com.springboot.boot.modules.admin.mapper.NewTableMapper;
import com.springboot.boot.modules.admin.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private NewTableMapper mapper;
    @Override
    public PageInfo<NewTable> searchAll(ClassifySearchAllDto dto) {
        if (dto.getPaging()){
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        NewTableExample ex=new NewTableExample();
        List<NewTable> newTables = mapper.selectByExample(ex);
        PageInfo<NewTable> pageInfo = new PageInfo<>(newTables);
        return pageInfo;
    }
}
