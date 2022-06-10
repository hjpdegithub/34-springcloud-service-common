package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.file.FileDto;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * controller {@link MultipartFile}
     */
//    ApiResult uploadArrByFtp(MultipartFile[] files, String description, Long userId);


  String  upload(MultipartFile file, FileDto fileDto);

}
