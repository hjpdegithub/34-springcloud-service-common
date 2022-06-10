package com.springboot.boot.common.enums;

/**
 * @author by zhangbr
 * @date 2020/3/24.
 */
public enum TestPassEnum {

    /**
     * 通用枚举
     */
    passed(1, "通过"),
    notPassed(2, "未通过");
    TestPassEnum(Integer code, String name) {
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
