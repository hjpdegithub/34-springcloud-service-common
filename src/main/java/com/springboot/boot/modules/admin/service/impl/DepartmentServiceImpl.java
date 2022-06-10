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
//        List<List<MpDepartment>> lists = groupList(mpDepartments, 3);
//        System.out.println(JSONObject.toJSON(lists));
        return ApiResult.success(mpDepartments);

    }
    /**
     * 集合拆分
     *
     * @param list     原集合
     * @param pageSize ⼦集合长度
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> groupList(List<T> list, int pageSize) {
        List<List<T>> listGroup = new ArrayList<List<T>>();
        int listSize = list.size();
        for (int i = 0; i < listSize; i += pageSize) {
            if (i + pageSize > listSize) {
                pageSize = listSize - i;
            }
            List<T> newList = list.subList(i, i + pageSize);
            listGroup.add(newList);
        }
        return listGroup;
    }
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        List<List<String>> lists = groupList(list, 2);
//        System.out.println("list:" + list.toString());
//        System.out.println(lists);
//    }

}
