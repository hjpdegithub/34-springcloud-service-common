package com.springboot.boot.modules.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;


@Data
@TableName("t_plan_info")
public class DaPlanInfo {

    private Long id;
    /**
     * 场景code
     */
    private String sceneCode;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 方案号
     */
    private String planCode;
    /**
     * 方案名称
     */
    private String planName;
    /**
     * 是否默认方案
     */
    private int isDefault;
    /**
     * 方案类型 1勤务保障
     */
    private int planType;
    /**
     * 等级 1  2 3
     */
    private int planLevel;

    private Timestamp createTime;

}