package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppCurriculumController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/app/curriculum")
@Slf4j
@Api(tags = "1.3", description = "前端课程展示【周京昊】")
@CrossOrigin
public class AppCurriculumController {

    @Autowired
    private CurriculumService curriculumService;
    @ApiOperation(value = "根据二级分类查询课程", notes="根据二级分类查询课程")
    @GetMapping(value="/searchCurrBySecond")
    public ApiResult searchCurrBySecond(@RequestParam("id") Long id,@RequestParam("classFormat") Integer classFormat,@RequestParam("className") String className) {
        log.info("根据二级分类查询课程============"+id,classFormat);
        List<MpCurriculum> allVo =  curriculumService.searchCurrBySecond(id,classFormat,className);
        return ApiResult.success(allVo);
    }
}
