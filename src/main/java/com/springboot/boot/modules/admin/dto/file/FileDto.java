package com.springboot.boot.modules.admin.dto.file;

import com.springboot.boot.common.constant.CommonConstant;
import com.springboot.boot.common.page.PageDto;
import lombok.Data;


@Data
public class FileDto extends PageDto {


    private String rootPath;

//    private String fileTypePath;

    private String fileName;

    private String fileFullPath;


}
