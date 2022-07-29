package com.springboot.boot.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "uds")
public class ConstantUdsProperties {
    private  String apiServiceUrl;
    private  String accessKey;
    private  String secretKey;


}
