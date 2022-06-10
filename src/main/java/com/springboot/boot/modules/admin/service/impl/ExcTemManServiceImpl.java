package com.springboot.boot.modules.admin.service.impl;


import com.alipay.api.domain.AttachmentInfo;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.mapper.ExcTemManMapper;
import com.springboot.boot.modules.admin.service.ExcTemManService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ExcTemManServiceImpl implements ExcTemManService {

    @Resource
    private ExcTemManMapper excTemManMapper;

    @Override
    public List<AttachmentInfo> attachmentFileSelectById(CommonAllDto commonAllDto) {
       return   excTemManMapper.selectExeTemUrlById(commonAllDto.getId());
    }
}
