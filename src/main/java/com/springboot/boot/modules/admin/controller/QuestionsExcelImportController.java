package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.service.QuestionsExcelService;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zjh
 * @version 1.0.0
 * @ClassName TestController
 * @Description TODO 导出导入test
 * @createTime 2020.09.06 13:49
 */
@RequestMapping("/mp/excel")
@RestController
@Slf4j
@Api(tags = "1.5.5", description = "题库导入【侯建鹏】")
public class QuestionsExcelImportController {

    @Autowired
    private QuestionsExcelService questionsExcelService;
    /**
     * 导入用户信息
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/questionsExcelImport")
    public ApiResult questionsExcelImport(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {
        String result = questionsExcelService.questionsExcelImport(file, id);
        if (!"sucess".equals(result)&&result.contains("(")) {
            return ApiResult.error(ApiCode.FAIL, "第"+result+"行出现错误，检查后重新导入");
        } else if (!"sucess".equals(result)&&!result.contains("(")){
            return ApiResult.error(ApiCode.FAIL, result);
        }
        else {
            return ApiResult.success("success");
        }
//        if ("singleChoiceFail".equals(result)) {
//            return ApiResult.error(ApiCode.FAIL, "单选题数量和试卷不一致");
//        }
//        if ("singleChoiceFail".equals(result)) {
//            return ApiResult.error(ApiCode.FAIL, "多选题数量和试卷不一致");
//        }
//        if ("singleChoiceFail".equals(result)) {
//            return ApiResult.error(ApiCode.FAIL, "判断题数量和试卷不一致");
//        }
//        if ("singleChoiceFail".equals(result)) {
//            return ApiResult.error(ApiCode.FAIL, "选择的试卷不存在");
//        }
//        return ApiResult.success("success");
    }
}
