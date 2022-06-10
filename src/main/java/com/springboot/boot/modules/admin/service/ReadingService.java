package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.entity.MpReading;

import java.util.List;

public interface ReadingService {
    /**
     * 浏览记录
     * @param commonDto
     */
    void addUser(CommonDto commonDto);

    List<MpReading> selectByBusinessId(CommonDto commonDto);
}
