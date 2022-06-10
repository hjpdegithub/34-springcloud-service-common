package com.springboot.boot.modules.admin.service;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.NewTable;

public interface TestService {

    PageInfo<NewTable> searchAll(ClassifySearchAllDto dto);
}
