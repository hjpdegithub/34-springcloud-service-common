package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.OptionDto;
import com.springboot.boot.modules.admin.entity.MpOption;
import com.springboot.boot.modules.admin.entity.MpOptionExample;
import com.springboot.boot.modules.admin.mapper.MpOptionMapper;
import com.springboot.boot.modules.admin.service.OptionService;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OptionServiceImpl
 * @Description TODO 选项实现
 * @Author jhzhou
 * @Date 2022/4/8 0008 14:41
 * @Version 2.0
 **/
@Service
@Slf4j
public class OptionServiceImpl implements OptionService {

    @Resource
    private MpOptionMapper optionMapper;

    /**
     * 选项对的新增
     *
     * @param optionDtos 选项
     */
    @Override
    public void addOptionByQuestion(List<OptionDto> optionDtos, long id, Long userId) {
        //排序
        OptionDto optionDto = null;
        if (null != optionDtos && optionDtos.size() > 0) {
            optionDto = optionDtos.get(0);
            if (optionDto.getOption() != null) {
                optionDtos.sort(Comparator.comparing(OptionDto::getOption));
            } else {
                optionDtos.sort(Comparator.comparing(OptionDto::getOpt));
            }
        }
        optionDtos.forEach(e -> {
            MpOption option = new MpOption();
            option.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
            option.setCreateTime(new Date());
            option.setCreateUser(userId);
            option.setDeleFlag(CommonEnum.USED.getCode());
            if (null != e.getOption()) {
                option.setOpt(e.getOption());
            } else {
                option.setOpt(e.getOpt());
            }

            option.setOptionName(e.getOptionName());
            option.setQuestionId(id);
            option.setUpdateUser(userId);
            option.setUpdateTime(new Date());
            optionMapper.insertSelective(option);
        });
    }

    /**
     * 选项编辑
     * 先删除在新增
     *
     * @param id
     * @param optionDtos
     * @param userId
     */
    @Override
    public void updateOptionByQuestion(Long id, List<OptionDto> optionDtos, Long userId) {
        //通过题库id删除选项相关数据
        MpOptionExample mpOptionExample = new MpOptionExample();
        MpOptionExample.Criteria criteria = mpOptionExample.createCriteria();
        criteria.andQuestionIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpOption> mpOptions = optionMapper.selectByExample(mpOptionExample);
        mpOptions.forEach(e -> {
            MpOption mpOption = new MpOption();
            mpOption.setId(e.getId());
            mpOption.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = optionMapper.updateByPrimaryKeySelective(mpOption);
            if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                throw new BusinessException("选项删除失败具体名称为" + e.getOptionName());
            }
        });
        //新增选项
        this.addOptionByQuestion(optionDtos, id, userId);
    }

    /**
     * 选项信息查询
     *
     * @param id
     * @return
     */
    @Override
    public List<MpOption> selectByQuestionId(Long id) {
        MpOptionExample example = new MpOptionExample();
        MpOptionExample.Criteria criteria = example.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andQuestionIdEqualTo(id);
        List<MpOption> mpOptions = optionMapper.selectByExample(example);
        return mpOptions;
    }

    @Override
    public void deleteOptionBYQuestion(Long id) {
        //查询选项信息
        MpOptionExample example = new MpOptionExample();
        MpOptionExample.Criteria criteria = example.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andQuestionIdEqualTo(id);
        List<MpOption> mpOptions = optionMapper.selectByExample(example);
        mpOptions.forEach(e -> {
            MpOption mpOption = new MpOption();
            mpOption.setId(e.getId());
            mpOption.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = optionMapper.updateByPrimaryKeySelective(mpOption);
            if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                throw new BusinessException("删除选项信息失败id:" + e.getId());
            }
        });
    }

    /**
     * 批量查询出选项信息
     *
     * @param optionId
     * @return
     */
    @Override
    public List<MpOption> selectByIds(List<Long> optionId) {
        MpOptionExample mpOptionExample = new MpOptionExample();
        MpOptionExample.Criteria criteria = mpOptionExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andIdIn(optionId);
        List<MpOption> mpOptions = optionMapper.selectByExample(mpOptionExample);
        return mpOptions;
    }

}
