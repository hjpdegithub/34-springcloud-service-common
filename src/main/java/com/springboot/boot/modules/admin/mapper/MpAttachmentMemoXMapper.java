package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentMemo;
import com.springboot.boot.modules.admin.entity.MpAttachmentMemoExample;
import com.springboot.boot.modules.admin.entity.MpCurthu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpAttachmentMemoXMapper {
    List<MpCurthu>   collectCurSearCh (@Param("dto") CurMemDto dto);

}