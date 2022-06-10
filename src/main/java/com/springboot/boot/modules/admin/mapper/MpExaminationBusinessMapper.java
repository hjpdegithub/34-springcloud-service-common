package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.examination.ExaminationSearchDto;
import com.springboot.boot.modules.admin.dto.examination.ExaminationSearchNoPageDto;
import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.entity.MpExaminationExample;
import com.springboot.boot.modules.admin.vo.examination.ExaminationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MpExaminationBusinessMapper {


    List<ExaminationVo> selectByDto(ExaminationSearchDto dto);


    List<ExaminationVo> searchNoPage(ExaminationSearchNoPageDto dto);





}