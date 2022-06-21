package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurriculumAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CurriculumController
 * @Description TODO 课程管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/questtionUpdownLoad")
@Slf4j
@Api(tags = "1.5.8", description = "考试模板的上传下载【侯建鹏】")
@CrossOrigin
public class questionsUpdownLoadController {

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation(value = "考试试题模板的上传", notes = "考试试题模板的上传")
    @PostMapping(value = "/questionTemplateUpload")
    public ApiResult questionTemplateUpload(@RequestParam("file") MultipartFile file
            , @RequestParam("type") Integer type
    ) {
        return attachmentService.attachmentDealQuestionTemplate(file, type);
    }


    @ApiOperation(value = "考试试题模板的下载", notes = "考试试题模板的下载")
    @PostMapping(value = "/questionTemplatedowndLoad")
    public ApiResult questionTemplatedowndLoad(HttpServletResponse response,
                                               @RequestParam("type") Integer type) {
        log.info("考试模板下载----------考试模板下载---{}");

        return attachmentService.questionTemplatedowndLoad(response, type);

    }

}
