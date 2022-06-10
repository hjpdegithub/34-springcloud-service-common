package com.springboot.boot.modules.admin.vo.examination;

import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RandomNumVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/15 0015 15:01
 * @Version 1.0
 **/
@Data
public class RandomNumVo {
    private List<MpQuestionBank> mpQuestionBanks;
    private Integer num;
    private Integer pontins;
    private Integer totalPotins;
    private Integer type;
}
