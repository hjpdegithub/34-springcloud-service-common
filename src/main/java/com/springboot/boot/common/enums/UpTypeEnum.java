package com.springboot.boot.common.enums;

/**
 * 类型相关枚举
 */
public enum UpTypeEnum {
    /**
     * 类型
     */
    notUp(0, "未上线"),
    up(1, "上线"),
    down(2, "下线"),

    ;


    UpTypeEnum(Integer code, String name) {
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
