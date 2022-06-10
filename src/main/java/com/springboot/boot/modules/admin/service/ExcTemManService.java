package com.springboot.boot.modules.admin.service;


import com.alipay.api.domain.AttachmentInfo;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;

import java.util.List;

public interface ExcTemManService {

 List<AttachmentInfo> attachmentFileSelectById(CommonAllDto commonAllDto);


}