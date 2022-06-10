package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.modules.admin.entity.MpUnit;
import com.springboot.boot.modules.admin.entity.MpUnitExample;
import com.springboot.boot.modules.admin.mapper.MpUnitMapper;
import com.springboot.boot.modules.admin.service.UnitService;
import com.springboot.boot.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UnitServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:02
 * @Version 1.0
 **/
@Service
@Slf4j
public class UnitServiceImpl implements UnitService {


    @Resource
    private MpUnitMapper unitMapper;
    /**
     * 单位查询
     * @return
     */
    @Override
    public ApiResult search() {
        MpUnitExample example = new MpUnitExample();
        List<MpUnit> mpUnits = unitMapper.selectByExample(example);
        return ApiResult.success(mpUnits);
    }
}
