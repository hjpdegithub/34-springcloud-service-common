package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurComReplyDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.dto.file.FileDtoNoPage;
import com.springboot.boot.modules.admin.service.*;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CurriculumController
 * @Description TODO 课程管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp")
@Slf4j
@Api(tags = "1.5.3", description = "url转Stream【侯建鹏】")
@CrossOrigin
public class OssUrlStreamTestController {

    @Autowired
    private OssUrlStreamTestService ossUrlStreamTestService;
    @SneakyThrows
    @ApiOperation(value = "1PDFurl转流展示", notes = "1PDFurl转流展示")
    @PostMapping(value = "/ossUrlToStream")
    public String x(HttpServletResponse response, @RequestBody FileDtoNoPage dto) {
        ossUrlStreamTestService.StreamOut(response, dto.getFileFullPath(),
                dto.getFileName()
        );
        return "sucess";
    }



    @SneakyThrows
    @ApiOperation(value = "1PDFurl转流展示", notes = "1PDFurl转流展示")
    @PostMapping(value = "/ossToLocalToShow")
    public ApiResult xx(HttpServletResponse response, @RequestBody FileDtoNoPage dto) {

       return   ApiResult.success(ossUrlStreamTestService.ossToLocalToShow(response, dto.getFileFullPath(),
                dto.getFileName()));


    }


}
