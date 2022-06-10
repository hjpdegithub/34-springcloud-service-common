package com.springboot.boot.common.http;

import lombok.Data;
import lombok.NonNull;

@Data
public class HttpResultDTO {
    // 响应码
    @NonNull
    private Integer code;

    // 响应体
    @NonNull
    private String body;
}
