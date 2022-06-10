package com.springboot.boot.modules.admin.service;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.TestUserDto;
import com.springboot.boot.modules.admin.dto.test.MpExaminationDto;
import com.springboot.boot.modules.admin.dto.test.TestResultDto;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.modules.admin.vo.test.TestResultPageDto;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TestResultService {
    /**
     * 选项的新增
     * @param dto 选项
     */
    PageInfo resultQuery(TestResultPageDto dto);
    void   export (HttpServletResponse response,   TestResultDto dto);
    List<MpExaminationVo>  mpExaminationList(MpExaminationDto dto);
    List<MpUserAuthenticationVo> testUserList(@RequestBody TestUserDto dto) ;


}
