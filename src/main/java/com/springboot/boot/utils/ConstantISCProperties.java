package com.springboot.boot.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "isc")
public class ConstantISCProperties {
    private String casValidateUrl;
    private String service;
    private String expMillis;
    private String logInUrl;
    private String logOutUrl;
}
