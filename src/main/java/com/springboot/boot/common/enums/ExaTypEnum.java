package com.springboot.boot.common.enums;

/**
 * 类型相关枚举
 */
public enum ExaTypEnum {
    /**
     * 类型
     */
    test(1, "考试类'"),
    question(2, "2问卷类");


    ExaTypEnum(Integer code, String name) {
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
