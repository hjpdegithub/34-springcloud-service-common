package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.TestCountVo;
import com.springboot.boot.modules.admin.vo.test.UsertestVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TestCountService {


     TestCountVo testCounts();

     List<UsertestVo>  myTestList(@RequestBody CommonAllDto dto  );

     List<MpExaminationVo>  questionList();

     List<MpExaminationVo>  testList();






}
