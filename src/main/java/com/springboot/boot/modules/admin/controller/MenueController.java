package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.curriculum.GetCurrMess;
import com.springboot.boot.modules.admin.vo.curriculum.SrearchAndClassifyVo;
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
@RequestMapping("/mp/app/menue")
@Slf4j
@Api(tags = "1.", description = "前端课程展示【周京昊】")
@CrossOrigin
public class MenueController {

    @Autowired
    private CurriculumService curriculumService;
    @ApiOperation(value = "根据二级分类查询课程", notes="根据二级分类查询课程")
    @GetMapping(value="/searchCurrBySecond")
    public ApiResult searchCurrBySecond(@RequestParam("id") Long id,@RequestParam("classFormat") Integer classFormat,@RequestParam("className") String className) {
        log.info("根据二级分类查询课程============"+id,classFormat);
        List<MpCurriculum> allVo =  curriculumService.searchCurrBySecond(id,classFormat,className);
        return ApiResult.success(allVo);
    }
    //3.0版本 重构
    @ApiOperation(value = "3.0版本 重构课程分类合并", notes="3.0版本 重构")
    @GetMapping(value="/searchCurrAndSecond")
    public ApiResult searchCurrAndSecond(@RequestParam("id") Long id,@RequestParam("classFormat") Integer classFormat,@RequestParam("className") String curriculumName) {
        log.info("查询二级分类和课程的相关信息============"+id,classFormat,curriculumName);
        if (null == id){
            ApiResult.error(500,"id不能为空");
        }
        List<SrearchAndClassifyVo>  allVo =  curriculumService.searchCurrAndSecond(id,classFormat,curriculumName);
        return ApiResult.success(allVo);
    }

    @ApiOperation(value = "3.0版本 重构课程分类合并", notes="3.0版本 重构")
    @GetMapping(value="/getCurrMess")
    public ApiResult getCurrMess(@RequestParam("id") Long id) {
        log.info("查询二级分类和课程的相关信息============"+id);
        if (null == id){
            ApiResult.error(500,"id不能为空");
        }
        GetCurrMess allVo =  curriculumService.getCurrMess(id);
        return ApiResult.success(allVo);
    }


}
