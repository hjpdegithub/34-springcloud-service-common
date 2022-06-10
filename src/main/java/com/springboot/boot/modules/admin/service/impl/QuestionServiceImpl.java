package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.QuestionDeleteDto;
import com.springboot.boot.modules.admin.dto.QuestionSerchDto;
import com.springboot.boot.modules.admin.entity.MpOption;
import com.springboot.boot.modules.admin.entity.MpOptionExample;
import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpQuestionBankExample;
import com.springboot.boot.modules.admin.mapper.MpQuestionBankBusinessMapper;
import com.springboot.boot.modules.admin.mapper.MpQuestionBankMapper;
import com.springboot.boot.modules.admin.service.OptionService;
import com.springboot.boot.modules.admin.service.QuestionService;
import com.springboot.boot.modules.admin.vo.examination.ExaminationVo;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName QuestionServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/8 0008 14:26
 * @Version 1.0
 **/
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private MpQuestionBankMapper questionBankMapper;
    @Resource
    private OptionService optionService;

    @Resource
    private MpQuestionBankBusinessMapper questionBankBusinessMapper;

    /**
     * 题库的新增以及修改
     *
     * @param dto
     * @return
     */
    @Override
    public ApiResult addOrUpdate(QuestionBankAddAndUpdateDto dto) {
        MpQuestionBank questionBank = new MpQuestionBank();
        BeanCopy.copy(dto, questionBank);
        questionBank.setUpdateUser(dto.getUserId());
        questionBank.setUpdateTime(new Date());
        questionBank.setDeleFlag(CommonEnum.USED.getCode());
        //处理正确答案
        List<String> rightAnswer = dto.getRightAnswer();
        StringBuffer stringBuffer = new StringBuffer();
        rightAnswer.stream().forEach(s -> {
            stringBuffer.append(s.toUpperCase()).
                    append(",");
        });
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        questionBank.setRightAnswer(stringBuffer.toString());
        //判断该接口新增还是修改
        if (null != dto.getId()) {
            //修改
            questionBank.setId(dto.getId());
            int i = questionBankMapper.updateByPrimaryKeySelective(questionBank);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("题库编辑失败");
            }
            optionService.updateOptionByQuestion(dto.getId(), dto.getOptionDtos(), dto.getUserId());
        } else {
            long id = SnowFlakeUtils.getFlowIdInstance().nextId();
            questionBank.setId(id);
            questionBank.setCreateTime(new Date());
            //新增
            questionBank.setCreateUser(dto.getUserId());
            int i = questionBankMapper.insertSelective(questionBank);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("题库新增失败");
            }
            //选项的新增
            optionService.addOptionByQuestion(dto.getOptionDtos(), id, dto.getUserId());

        }
        return ApiResult.success();
    }

    /**
     * 试卷检查
     *
     * @param dto
     * @return
     */
    @Override
    public List<MpQuestionBank> selectByExIdAndType(QuestionBankAddAndUpdateDto dto) {
        MpQuestionBankExample bankExample = new MpQuestionBankExample();
        MpQuestionBankExample.Criteria criteria = bankExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andExaminationIdEqualTo(dto.getExaminationId());
        if (null != dto.getType()) {
            criteria.andTypeEqualTo(dto.getType());
        }
        List<MpQuestionBank> mpQuestionBanks = questionBankMapper.selectByExample(bankExample);
        return mpQuestionBanks;
    }

    /**
     * 题库查询接口
     *
     * @param dto
     * @return
     */
    @Override
    public PageInfo<QuestionSearchVo> search(QuestionSerchDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        List<QuestionSearchVo> questionSearchVos = questionBankBusinessMapper.selectQuestionAll(dto);
        if (!questionSearchVos.isEmpty()) {
            questionSearchVos.forEach(e -> {
                List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                e.setOptions(mpOptions);

            });
        }
        log.info("题库查询接口================vo" + JSONObject.toJSONString(questionSearchVos));
        PageInfo<QuestionSearchVo> pageInfo = new PageInfo<>(questionSearchVos);
        return pageInfo;
    }

    /**
     * 题库回显接口
     *
     * @param id
     * @return
     */
    @Override
    public QuestionSearchVo searchById(Long id) {
        QuestionSearchVo vo = new QuestionSearchVo();
        MpQuestionBankExample mpOptionExample = new MpQuestionBankExample();
        MpQuestionBankExample.Criteria criteria = mpOptionExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpQuestionBank> mpQuestionBanks = questionBankMapper.selectByExample(mpOptionExample);
        MpQuestionBank questionBank = null;
        if (null != mpQuestionBanks && mpQuestionBanks.size() > 0) {
            questionBank = mpQuestionBanks.get(0);
            BeanCopy.copy(questionBank, vo);
            List<MpOption> mpOptions = optionService.selectByQuestionId(questionBank.getId());
            vo.setOptions(mpOptions);
            return vo;
        }
        else{
            return null;
        }
    }

    /**
     * 题库删除接口
     *
     * @param deleteDto
     * @return
     */
    @Override
    public ApiResult delete(QuestionDeleteDto deleteDto) {
        //删除题库
        if (null != deleteDto.getIds() && deleteDto.getIds().size() > 0) {
            List<Long> ids = deleteDto.getIds();
            for (Long id : ids) {
                MpQuestionBank mpQuestionBank = new MpQuestionBank();
                mpQuestionBank.setId(id);
                mpQuestionBank.setDeleFlag(CommonEnum.DELETE.getCode());
                int i = questionBankMapper.updateByPrimaryKeySelective(mpQuestionBank);
                if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                    throw new BusinessException("题库删除失败");
                }
                //查询题库中选项的信息并删除
                optionService.deleteOptionBYQuestion(id);
            }
            return ApiResult.success();
        } else {
            MpQuestionBank mpQuestionBank = new MpQuestionBank();
            mpQuestionBank.setId(deleteDto.getId());
            mpQuestionBank.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = questionBankMapper.updateByPrimaryKeySelective(mpQuestionBank);
            if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                throw new BusinessException("题库删除失败");
            }
            //查询题库中选项的信息并删除
            optionService.deleteOptionBYQuestion(deleteDto.getId());
            return ApiResult.success();
        }
    }

    /**
     * 查询错误题库
     *
     * @param ids
     * @return
     */
    @Override
    public List<MpQuestionBank> selectByIds(List<Long> ids) {
        MpQuestionBankExample questionBankExample = new MpQuestionBankExample();
        MpQuestionBankExample.Criteria criteria = questionBankExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andIdIn(ids);
        List<MpQuestionBank> questionBanks = questionBankMapper.selectByExample(questionBankExample);

        return questionBanks;
    }
}
