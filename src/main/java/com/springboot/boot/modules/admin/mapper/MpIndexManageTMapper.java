package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpIndexManage;
import com.springboot.boot.modules.admin.entity.MpIndexManageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpIndexManageTMapper {

    List<MpIndexManage> selectAll(MpIndexManageExample example);


}