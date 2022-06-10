package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.entity.MpReading;
import com.springboot.boot.modules.admin.entity.MpReadingExample;
import com.springboot.boot.modules.admin.mapper.MpReadingMapper;
import com.springboot.boot.modules.admin.service.ReadingService;
import com.springboot.boot.utils.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ReadingServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/28 0028 15:46
 * @Version 1.0
 **/
@Service
public class ReadingServiceImpl implements ReadingService {

    @Resource
    private MpReadingMapper readingMapper;
    @Override
    public void addUser(CommonDto commonDto) {

        MpReading mpReading = new MpReading();
        mpReading.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
        mpReading.setCurriculumId(commonDto.getBusinessId());
        mpReading.setUserId(123L);
        mpReading.setMessage("1223浏览了课程");
        readingMapper.insertSelective(mpReading);

    }

    @Override
    public List<MpReading> selectByBusinessId(CommonDto commonDto) {
        MpReadingExample example = new MpReadingExample();
        example.createCriteria().andCurriculumIdEqualTo(commonDto.getBusinessId());
        List<MpReading> mpReadings = readingMapper.selectByExample(example);
        return mpReadings;
    }
}
