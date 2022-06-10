package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface QuestionsExcelService {
    public String questionsExcelImport(MultipartFile file, Long id) throws IOException;
}



