package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.utils.ApiResult;


public interface CurEliteService {
    /**
     * 精品课程查询
     * @return
     */
    ApiResult curEliteSelect();



    ApiResult curSecondDetailSelectById(CommonAllDto dto) ;





}
