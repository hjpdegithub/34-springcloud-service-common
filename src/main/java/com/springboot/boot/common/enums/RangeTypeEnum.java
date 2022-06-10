package com.springboot.boot.common.enums;

/**
 * 类型相关枚举
 */
public enum RangeTypeEnum {
    /**
     * 类型
     */
    dataTest(1, "数据中台门户考试"),
    ability(2, "能力开放平台活动");


    RangeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
