package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.examination.ExaminationAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.examination.ExaminationRuleDto;
import com.springboot.boot.modules.admin.entity.MpExaminationRule;
import com.springboot.boot.modules.admin.entity.MpExaminationRuleExample;
import com.springboot.boot.modules.admin.mapper.MpExaminationRuleMapper;
import com.springboot.boot.modules.admin.service.ExaminationRuleService;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExaminationRuleServiceImpl
 * @Description TODO 试卷规则
 * @Author jhzhou
 * @Date 2022/4/7 0007 15:36
 * @Version 2.0
 **/
@Service
@Slf4j
public class ExaminationRuleServiceImpl implements ExaminationRuleService {

    @Resource
    private MpExaminationRuleMapper ruleMapper;
    /**
     * 试卷规则添加
     * @param rule 规则
     * @param code 编码 {@link com.springboot.boot.common.enums.TypeEnum}
     * @param dto 试卷实体
     */
    @Override
    public void addExamRule(ExaminationRuleDto rule, String code, ExaminationAddOrUpdateDto dto,long id) {
        MpExaminationRule examinationRule = new MpExaminationRule();
        examinationRule.setDeleFlag(CommonEnum.USED.getCode());
        examinationRule.setUpdateUser(dto.getUserId());
        examinationRule.setUpdateTime(new Date());
        examinationRule.setCreateUser(dto.getUserId());
        examinationRule.setCreateTime(new Date());
        BeanCopy.copy(dto,examinationRule);
        BeanCopy.copy(rule,examinationRule);
        examinationRule.setSubjectName(code);
        examinationRule.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
        examinationRule.setExamId(id);
        int i = ruleMapper.insertSelective(examinationRule);
        if (i <= CommonEnum.ADD_ERROR.getCode()){
            throw new BusinessException("新增试卷规则失败！code是："+code);
        }
    }

    /**
     * 试卷规则编辑
     * @param rule
     * @param code
     * @param dto
     * @param id
     * @param ruleId
     */
    @Override
    public void updateExanRule(ExaminationRuleDto rule, String code, ExaminationAddOrUpdateDto dto, Long id, Long ruleId) {
        MpExaminationRule examinationRule = new MpExaminationRule();
        examinationRule.setDeleFlag(CommonEnum.USED.getCode());
        examinationRule.setUpdateUser(dto.getUserId());
        examinationRule.setUpdateTime(new Date());
        BeanCopy.copy(dto,examinationRule);
        BeanCopy.copy(rule,examinationRule);
        examinationRule.setSubjectName(code);
        examinationRule.setId(ruleId);
        examinationRule.setExamId(id);
        int i = ruleMapper.updateByPrimaryKeySelective(examinationRule);
        if (i <= CommonEnum.ADD_ERROR.getCode()){
            throw new BusinessException("编辑试卷规则失败！code是："+code);
        }

    }

    /**
     * 试卷规则删除接口
     * 先查询在删除
     * @param id 试卷主键id
     * @return
     */

    @Override
    public int deleteByExamintionId(Long id) {
        //查询改试卷下的规则
        MpExaminationRuleExample ruleExample = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria criteria = ruleExample.createCriteria();
        criteria.andExamIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExaminationRule> mpExaminationRules = ruleMapper.selectByExample(ruleExample);
        mpExaminationRules.forEach(e->{
            MpExaminationRule rule = new MpExaminationRule();
            rule.setId(e.getId());
            rule.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = ruleMapper.updateByPrimaryKeySelective(rule);
             if (i <= CommonEnum.DELETE_ERROR.getCode()){
                 throw  new BusinessException("试卷规则删除失败 具体规则名称为："+e.getSubjectName());
             }
        });
        return 1;
    }

    /**
     * 试卷规则查询id
     * @param id 通过id
     * @return
     */
    @Override
    public List<MpExaminationRule> selectById(Long id) {
        //通过试卷id查询规则
        MpExaminationRuleExample examinationRuleExample = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria criteria = examinationRuleExample.createCriteria();
        criteria.andExamIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExaminationRule> mpExaminationRules = ruleMapper.selectByExample(examinationRuleExample);
        log.info("通过试卷id查询规则输出数据================{}", JSONObject.toJSON(mpExaminationRules));
        return mpExaminationRules;
    }
}
