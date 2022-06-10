package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {


 ApiResult attachmentDeal( @RequestParam("file")
         MultipartFile file);


  ApiResult attachmentFileSelect( CommonDto commonDto);

//  ApiResult attachmentFileListSelect( FileDto fileDto);
//
// ApiResult  attachmentFileDelete( CommonAllDto commonDto);
//
//
// ApiResult  attachmentFileUpdate( MultipartFile file, AttachmentInfoDto attachmentInfoDto);

 /**
  * 删除及新增附件
  * @param id 附件id
  * @param business 业务类型
  * @param businessId 业务id
  * @return
  */
 ApiResult addBusinessFile(Long id,String business,Long businessId);





}