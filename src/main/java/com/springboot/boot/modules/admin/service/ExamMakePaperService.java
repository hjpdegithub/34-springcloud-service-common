package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.exanmake.ExamMakeSearchDto;
import com.springboot.boot.modules.admin.dto.exanmake.MakerPaperButtonDto;
import com.springboot.boot.utils.ApiResult;

public interface ExamMakePaperService {

    ApiResult searchExamMake(ExamMakeSearchDto makeSearchDto);

    /**
     * 判卷详情接口
     * @param id
     * @return
     */
    ApiResult makePaper(Long id);

    /**
     * 判卷提交接口
     * @param makerPaperButtonDto
     * @return
     */
    ApiResult makePaperButton(MakerPaperButtonDto makerPaperButtonDto);
}
