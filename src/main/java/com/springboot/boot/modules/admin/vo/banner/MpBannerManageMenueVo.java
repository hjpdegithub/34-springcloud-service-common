package com.springboot.boot.modules.admin.vo.banner;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.vo.file.MpAttachmentInfoVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ChengjiVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/18 0018 14:15
 * @Version 1.0
 **/
@Data
public class MpBannerManageMenueVo {
    private String name;
    private List<MpBannerBelongModuleVo>  MpBannerBelongModuleVos;

}
