package com.springboot.boot.modules.admin.service.impl;


import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.test.MpUserAuthenticationDto;
import com.springboot.boot.modules.admin.entity.MpUserAuthentication;
import com.springboot.boot.modules.admin.entity.MpUserAuthenticationExample;
import com.springboot.boot.modules.admin.mapper.MpUserAuthenticationMapper;
import com.springboot.boot.modules.admin.service.TestNoManageService;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import com.springboot.boot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestNoManageServiceImpl implements TestNoManageService {

    @Resource
    private MpUserAuthenticationMapper mpUserAuthenticationMapper;

    @Override
    public ApiResult testNoApply(MpUserAuthenticationDto dto) {
//        MpUserAuthenticationExample example = new MpUserAuthenticationExample();
//        example.createCriteria().andNumberEqualTo(dto.getNumber());
//        List<MpUserAuthentication> mpUserAuthenticationList = mpUserAuthenticationMapper.selectByExample(example);
//        if (mpUserAuthenticationList != null && mpUserAuthenticationList.size() != 0) {
//            return 9;
//        }

//   json格式
//        {
//                "departmentName": "部门名称",
//                "name": "侯建鹏",
//                "phone": "123",
//                "unitName": "单位名称"
//        }
//


        //判断手机号是不是已经存在
        MpUserAuthenticationExample example0 = new MpUserAuthenticationExample();

        example0.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andPhoneEqualTo(dto.getPhone());
        long c1 = mpUserAuthenticationMapper.countByExample(example0);

        if (c1 > 0) {

            return ApiResult.error("该手机号已经申请了考试编号，请输入手机号查询考试编号");

        }

        MpUserAuthentication record = new MpUserAuthentication();
        BeanCopy.copy(dto, record);
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //文件的ID主键
        record.setId(snowFlakeUtil.nextId());
        Integer retNumBer = null;
        record.setNumber(retNumBer);
        Boolean a = null;
        do {

            retNumBer = StringUtil.getRandomOrderNumberInteger();
            MpUserAuthenticationExample example = new MpUserAuthenticationExample();
            example.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andNumberEqualTo(retNumBer);
            long c = mpUserAuthenticationMapper.countByExample(example);
            if (c > 0) {
                a = true;
            } else {
                a = false;
            }

        } while (a);

        record.setNumber(retNumBer);
        record.setCreateTime(new Date());
        record.setDeleFlag(CommonEnum.USED.getCode());
        mpUserAuthenticationMapper.insert(record);
        return ApiResult.success(record);
    }


    @Override
    public ApiResult queryTestNoByPhoneNo(MpUserAuthenticationDto dto) {
        //   json格式
        //  {
        //  "phone": "123",
        // }

        MpUserAuthenticationExample example = new MpUserAuthenticationExample();
        example.createCriteria().andPhoneEqualTo(dto.getPhone()).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpUserAuthentication> mpUserAuthenticationList = mpUserAuthenticationMapper.selectByExample(example);
        if (mpUserAuthenticationList != null && mpUserAuthenticationList.size() != 0) {

            return ApiResult.success(mpUserAuthenticationList.get(0));
        } else {
            return null;
        }
    }

    @Override
    public MpUserAuthenticationVo TestNoVerifys(MpUserAuthenticationDto dto) {
        //   json格式
        //  {
        //  "number": "148029"
        // }
        MpUserAuthenticationExample example = new MpUserAuthenticationExample();
        example.createCriteria().andNumberEqualTo(dto.getNumber()).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpUserAuthentication> mpUserAuthenticationList = mpUserAuthenticationMapper.selectByExample(example);
        if (mpUserAuthenticationList != null && mpUserAuthenticationList.size() != 0) {
            MpUserAuthenticationVo mpUserAuthenticationVo = new MpUserAuthenticationVo();
            BeanCopy.copy(mpUserAuthenticationList.get(0), mpUserAuthenticationVo);
            return mpUserAuthenticationVo;
        } else {
            return null;
        }

    }

    @Override
    public Integer TestNoVerifysEdit(MpUserAuthenticationDto dto) {

        //先判断电话号码是不是重复
        String phoneNubner = dto.getPhone();
        if (null != phoneNubner && !"".equals(phoneNubner)) {

            MpUserAuthenticationExample example = new MpUserAuthenticationExample();
            example.createCriteria().andPhoneEqualTo(phoneNubner).andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andIdNotEqualTo(dto.getId())
            ;
            List<MpUserAuthentication> retList = mpUserAuthenticationMapper.selectByExample(example);

            if (null != retList && retList.size() > 0) {

                throw new BusinessException("输入的电话号码已经注册");
            }
        }
        MpUserAuthentication record = new MpUserAuthentication();
        BeanCopy.copy(dto, record);
        record.setUpdateUser(dto.getUserId());
        record.setUpdateTime(new Date());
        int i = mpUserAuthenticationMapper.updateByPrimaryKeySelective(record);
        if (i <= 0) {
            throw new BusinessException("认证信息更新失败");
        }
        return i;

    }


}
