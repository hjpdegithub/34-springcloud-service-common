package com.springboot.boot.modules.admin.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {

    void excelExport(HttpServletResponse response) throws IOException;

    void excelImport(MultipartFile file) throws IOException;
}

