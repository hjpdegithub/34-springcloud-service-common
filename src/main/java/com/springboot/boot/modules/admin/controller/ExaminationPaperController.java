package com.springboot.boot.modules.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.examination.*;
import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.ExaminationPaperService;
import com.springboot.boot.modules.admin.service.QuestionService;
import com.springboot.boot.modules.admin.vo.examination.ExaminationVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ExaminationPaperController
 * @Description TODO 试卷管理
 * @Author jhzhou
 * @Date 2022/4/7 0007 14:06
 * @Version 2.0
 **/
@RestController
@RequestMapping("/mp/examinationPaper")
@Slf4j
@Api(tags = "2.0", description = "试卷管理【周京昊】")
@CrossOrigin
public class ExaminationPaperController {

    @Resource
    private ExaminationPaperService paperService;

    @Resource
    private QuestionService questionService;

    @ApiOperation(value = "试卷的新增以及修改", notes = "该接口根据试卷的主键id判断编辑传入")
    @PostMapping(value = "/addOrUpdate")
    public ApiResult addOrUpdate(@RequestBody ExaminationAddOrUpdateDto dto) {
        log.info("试卷的新增以及修改" + JSONObject.toJSONString(dto));
        //非空校验
        if (null != dto.getSingleChoiceNum() && null != dto.getSingleRule().getSubjectNum()) {
            if (dto.getSingleChoiceNum() < dto.getSingleRule().getSubjectNum()) {
                return ApiResult.error("500", "单选抽取数量大于题目数量无法创建");
            }
        }
        //非空校验
        if (null != dto.getMultipleChoiceNum() && null != dto.getMultipleRule().getSubjectNum()) {
            if (dto.getMultipleChoiceNum() < dto.getMultipleRule().getSubjectNum()) {
                return ApiResult.error("500", "多选抽取数量大于题目数量无法创建");
            }
        }
        //非空校验
        if (null != dto.getJudgeNum() && null != dto.getJudgeRule().getSubjectNum()) {
            if (dto.getJudgeNum() < dto.getJudgeRule().getSubjectNum()) {
                return ApiResult.error("500", "判断抽取数量大于题目数量无法创建");
            }
        }
        //分析题版本加入字段
        //非空校验
        if (null != dto.getAnalysisNum() && null != dto.getAnalysisRule().getSubjectNum()) {
            if (dto.getAnalysisNum() < dto.getAnalysisRule().getSubjectNum()) {
                return ApiResult.error("500", "分析抽取数量大于题目数量无法创建");
            }
        }
        //计算抽题数量
        Integer j = 0;
        if (dto.getJudgeRule() != null
                && dto.getJudgeRule().getSubjectNum() != null
                && dto.getJudgeRule().getFraction() != null
        ) {
            j = dto.getJudgeRule().getSubjectNum() * dto.getJudgeRule().getFraction();
        }
        Integer s = 0;
        if (dto.getSingleRule() != null
                && dto.getSingleRule().getSubjectNum() != null
                && dto.getSingleRule().getFraction() != null
        )
            s = dto.getSingleRule().getSubjectNum() * dto.getSingleRule().getFraction();
        Integer m = 0;
        if (dto.getMultipleRule() != null
                && dto.getMultipleRule().getSubjectNum() != null
                && dto.getMultipleRule().getFraction() != null
        ) {
            m = dto.getMultipleRule().getSubjectNum() * dto.getMultipleRule().getFraction();
        }
        //分析版本加入
        Integer a = 0;
        if (dto.getAnalysisRule() != null
                && dto.getAnalysisRule().getSubjectNum() != null
                && dto.getAnalysisRule().getFraction() != null){
            a = dto.getAnalysisRule().getSubjectNum() * dto.getAnalysisRule().getFraction();
        }
        if (null != dto.getPaper()) {
            if (dto.getPaper() != (j + s + m + a)) {
                return ApiResult.error("500", "试卷不等于题目总分数无法创建");
            }
        }

        log.info("试卷的新增以及修改========={}", JSONObject.toJSON(dto));
        if (null == dto.getId()){
            List<MpExamination> mpExamination = paperService.selectByName(dto);
            if (!CollectionUtils.isEmpty(mpExamination)){
                return ApiResult.error("试卷名称重复");
            }
        }

        ApiResult result = paperService.addOrUpdate(dto);
        return result;
    }

    @ApiOperation(value = "试卷管理查询接口", notes = "试卷管理查询接口")
    @PostMapping(value = "/searchNoPage")
    public ApiResult searchNoPage(@RequestBody ExaminationSearchNoPageDto dto) {
        log.info("试卷管理查询接口========={}", JSONObject.toJSON(dto));
        List<ExaminationVo> result = paperService.searchNoPage(dto);
        return ApiResult.success(result);

    }

    @ApiOperation(value = "试卷管理查询接口", notes = "试卷管理查询接口")
    @PostMapping(value = "/search")
    public ApiResult search(@RequestBody ExaminationSearchDto dto) {
        log.info("试卷管理查询接口========={}", JSONObject.toJSON(dto));
        PageInfo<ExaminationVo> result = paperService.search(dto);
        return ApiResult.success(result);

    }

    @ApiOperation(value = "试卷回显", notes = "试卷回显")
    @PostMapping(value = "/select")
    public ApiResult select() {
        //查询试卷信息
        ApiResult result = paperService.select();
        return result;
    }

    @ApiOperation(value = "试卷管回显接口", notes = "试卷管回显接口")
    @GetMapping(value = "/selectById")
    public ApiResult selectById(@RequestParam("id") Long id) {
        log.info("试卷管回显接口========={}", JSONObject.toJSON(id));
        ApiResult result = paperService.selectById(id);
        return result;
    }


    @ApiOperation(value = "试卷删除接口", notes = "试卷删除接口")
    @PostMapping(value = "/delete")
    public ApiResult deleteById(@RequestBody ExaminationDeleteDto dto) {
        log.info("试卷删除接口========={}", JSONObject.toJSON(dto));
        ApiResult result = paperService.delete(dto);
        return result;
    }

    @ApiOperation(value = "通过id查询试卷信息接口", notes = "通过id查询试卷信息接口")
    @PostMapping(value = "/selectExamById")
    public ApiResult selectExamById(@RequestParam("id") Long id) {
        log.info("通过id查询试卷信息接口========={}", JSONObject.toJSON(id));
        MpExamination examination = paperService.selectExamById(id);
        return ApiResult.success(examination);
    }

    @ApiOperation(value = "上下线切换接口", notes = "上下线切换接口")
    @PostMapping(value = "/SwitchUp")
    public ApiResult SwitchUp(@RequestBody ExaminationSwitchUpDto switchUpDto) {
        log.info("上下线切换接口========={}", JSONObject.toJSON(switchUpDto));
        //查询试卷信息
        MpExamination examination = paperService.selectExamById(switchUpDto.getId());
        //查看试卷下题库的数量
        QuestionBankAddAndUpdateDto questionBankAddAndUpdateDto = new QuestionBankAddAndUpdateDto();
        questionBankAddAndUpdateDto.setExaminationId(switchUpDto.getId());
        List<MpQuestionBank> mpQuestionBanks = questionService.selectByExIdAndType(questionBankAddAndUpdateDto);
        //过滤
        List<MpQuestionBank> singles = mpQuestionBanks.stream().filter(a -> a.getType() == 1).collect(Collectors.toList());
        List<MpQuestionBank> ms = mpQuestionBanks.stream().filter(a -> a.getType() == 2).collect(Collectors.toList());
        List<MpQuestionBank> js = mpQuestionBanks.stream().filter(a -> a.getType() == 3).collect(Collectors.toList());
        //分析题版本加入
        List<MpQuestionBank> as = mpQuestionBanks.stream().filter(a -> a.getType() == 4).collect(Collectors.toList());
        //如果上线判断数量是否相同
        if (switchUpDto.getUpType() == CommonEnum.UP.getCode()) {
            if (singles.size() != examination.getSingleChoiceNum()) {
                return ApiResult.error(500, "选择题数量不足或已超出无法上线");
            }
            if (ms.size() != examination.getMultipleChoiceNum()) {
                return ApiResult.error(500, "多选题数量不足或已超出无法上线");
            }
            //3.0版本加入分析
            if (!CollectionUtils.isEmpty(as) && as.size() != examination.getAnalysisNum()){
                return ApiResult.error(500, "分析题数量不足或已超出无法上线");
            }
            if (examination.getExaminationType() == 1 || examination.getExaminationType() == 3) {
                if (js.size() != examination.getJudgeNum()) {
                    return ApiResult.error(500, "判断题数量不足或已超出无法上线");
                }
            }

        }
        ApiResult result = paperService.SwitchUp(switchUpDto);
        return result;
    }


}
