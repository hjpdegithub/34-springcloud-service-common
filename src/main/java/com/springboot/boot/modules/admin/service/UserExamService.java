package com.springboot.boot.modules.admin.service;

public interface UserExamService {
    /**
     * 考试次数
     * @param id
     * @param userId
     * @return
     */
    int selectExamByCount(Long id, Long userId);
}
