package com.springboot.boot.filters;



import com.springboot.boot.config.MyFilterT;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.DispatcherType;

//@Configuration  //定义此类为配置类
public class FilterConfig {


//    @Bean
//    public FilterRegistrationBean myFilterRegistrationBean(){
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new MyFilterT());
//        //添加过滤路径
//        filterRegistrationBean.addUrlPatterns("/mp/*","/sso/*","/token/getToken");
//        return filterRegistrationBean;
//    }
    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new MyFilterT());
        registration.addUrlPatterns("/*");
        registration.setName("myFilter");
        registration.setOrder(Integer.MAX_VALUE);
        //添加过滤路径
        registration.addUrlPatterns("/mp/*","/sso/*","/token/getToken");
        return registration;
    }




}