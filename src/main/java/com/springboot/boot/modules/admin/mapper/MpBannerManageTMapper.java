package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.entity.MpBannerManage;
import com.springboot.boot.modules.admin.entity.MpBannerManageExample;
import com.springboot.boot.modules.admin.vo.banner.MpBannerBelongModuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpBannerManageTMapper {
    List<MpBannerManage> selectAll(@Param("dto") BannerManageDto dto);
    List<MpBannerBelongModuleVo> listforShow(@Param("dto") BannerManageNoPageDto bannerManageDto);

}