package com.springboot.boot.modules.admin.service.impl;

import com.alipay.api.domain.AttachmentInfo;
import com.springboot.boot.modules.admin.dto.AttachmentInfoListDto;
import com.springboot.boot.modules.admin.service.FileByDbService;
import com.springboot.boot.modules.admin.vo.AttachmentInfoVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
@Service
public class FileByDbServiceImpl implements FileByDbService {

//    @Resource
//    private AttachmentInfoMapper attachmentInfoMapper;
//    /**
//     * 文件上传操作数据库
//     * @param attachmentInfoListDto 文件对象集合
//     * @return
//     */
//    @Override
//    public ApiResult<List<AttachmentInfoVo>> uploadByFtp(AttachmentInfoListDto attachmentInfoListDto) {
//        if (Objects.isNull(attachmentInfoListDto) || CollectionUtils.isEmpty(attachmentInfoListDto.getAttachmentInfoDtoList())) {
//            return new ApiResult(ApiCode.FILE_FAIL.getCode(), ApiCode.FILE_FAIL.getMessage(), null);
//        }
//        List<AttachmentInfoVo> infoVoList = new ArrayList<AttachmentInfoVo>();
//        List<AttachmentInfoVo> attList=attachmentInfoListDto.getAttachmentInfoDtoList();
//        for (AttachmentInfoVo infoDto : attList) {
//            AttachmentInfo eshopAttachmentInfo = BeanCopy.copy(infoDto,new AttachmentInfo());
//            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
//            eshopAttachmentInfo.setId(snowFlakeUtil.nextId());
//            int add = attachmentInfoMapper.insertSelective(eshopAttachmentInfo);
//            if (add > 0) {
//                AttachmentInfoVo addVo = new AttachmentInfoVo();
//                addVo.setId(eshopAttachmentInfo.getId()+"");
//                addVo.setFileName(eshopAttachmentInfo.getFileName());
//                addVo.setFileUrl(eshopAttachmentInfo.getFileUrl());
//                infoVoList.add(addVo);
//            }
//        }
//        log.info("文件业务数据存储完毕 {}", System.currentTimeMillis());
//        return new ApiResult(ApiCode.SUCCESS.getCode(),ApiCode.SUCCESS.getMessage(),infoVoList);
//    }
}
