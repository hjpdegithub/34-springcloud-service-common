package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.entity.MpSignUp;
import com.springboot.boot.modules.admin.entity.MpSignUpExample;
import com.springboot.boot.modules.admin.mapper.MpSignUpMapper;
import com.springboot.boot.modules.admin.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SignUpServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 18:06
 * @Version 1.0
 **/
@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    @Resource
    private MpSignUpMapper mpSignUpMapper;

    /**
     * 查询该用户是否报名
     * 查看报名人数
     * @param id
     * @param userId
     * @return
     */
    @Override
    public  List<MpSignUp> selectByIdAndUserId(Long id, Long userId) {
        MpSignUpExample mpSignUpExample = new MpSignUpExample();
        MpSignUpExample.Criteria criteria = mpSignUpExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        if (null != userId){
            criteria.andUserIdEqualTo(userId);
        }
        criteria.andExamIdEqualTo(id);
        List<MpSignUp> mpSignUps = mpSignUpMapper.selectByExample(mpSignUpExample);
        return mpSignUps;
    }

    /**
     * 点击报名
     * @param signUp
     */
    @Override
    public void insert(MpSignUp signUp) {
        int i = mpSignUpMapper.insertSelective(signUp);
        if (i <= CommonEnum.ADD_ERROR.getCode()){
            throw new BusinessException("点击报名错误");
        }
    }

    /**
     * 取消报名接口
     * @param signUp
     */
    @Override
    public void delete(MpSignUp signUp) {
        int i = mpSignUpMapper.updateByPrimaryKeySelective(signUp);
        if (i <= CommonEnum.UPDATE_ERROR.getCode()){
            throw new BusinessException("取消报名失败！");
        }
    }
}
