package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.modules.admin.entity.MpDepartment;
import com.springboot.boot.modules.admin.entity.MpDepartmentExample;
import com.springboot.boot.modules.admin.mapper.MpDepartmentMapper;
import com.springboot.boot.modules.admin.service.DepartmentService;
import com.springboot.boot.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO 部门实现类
 * @Author jhzhou
 * @Date 2022/4/7 0007 17:11
 * @Version 2.0
 **/
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {


    @Resource
    private MpDepartmentMapper departmentMapper;

    /**
     * 部门查询
     * @return
     */
    @Override
    public ApiResult search() {
        MpDepartmentExample example = new MpDepartmentExample();
        List<MpDepartment> mpDepartments = departmentMapper.selectByExample(example);
        return ApiResult.success(mpDepartments);

    }
}
