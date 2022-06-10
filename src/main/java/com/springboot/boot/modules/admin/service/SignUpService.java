package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.entity.MpSignUp;

import java.util.List;

public interface SignUpService {
    /**
     * 查询该用户是否报名
     * 查看报名人数
     * @param id
     * @param userId
     * @return
     */
    List<MpSignUp> selectByIdAndUserId(Long id, Long userId);

    /**
     * 报名新增
     * @param signUp
     */
    void insert(MpSignUp signUp);

    /**
     * 报名删除
     * @param signUp
     */
    void delete(MpSignUp signUp);
}
