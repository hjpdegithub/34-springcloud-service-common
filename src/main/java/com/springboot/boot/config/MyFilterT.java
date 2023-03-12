package com.springboot.boot.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgcc.isc.ualogin.client.CASClient;
import com.sgcc.isc.ualogin.client.CASTicket;
import com.sgcc.isc.ualogin.client.IscServiceTicketValidator;
import com.sgcc.isc.ualogin.client.util.Base64Util;
import com.sgcc.isc.ualogin.client.util.IscSSOResourceUtil;
import com.sgcc.isc.ualogin.client.vo.IscSSOUserBean;
import com.springboot.boot.utils.ConstantISCProperties;
import com.springboot.boot.utils.TokenCheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//过滤器
//@WebFilter(urlPatterns = {"/mp/*", "/sso/*"})
@Order(value = 1)
public class MyFilterT implements Filter {
    @Autowired
    private ConstantISCProperties constantISCProperties;
    @Autowired
    private TokenCheckUtil tokenCheckUtil;
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/token/getToken", "/swagger-ui.html", "/favicon.ico")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init-----------filter");
        //filter注入Bean
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("tokenCheckUtil") != null && tokenCheckUtil == null) {
            tokenCheckUtil = (TokenCheckUtil) cxt.getBean("tokenCheckUtil");
        }
        if (cxt != null && cxt.getBean("constantISCProperties") != null && constantISCProperties == null) {
            constantISCProperties = (ConstantISCProperties) cxt.getBean("constantISCProperties");
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) response;
        //跨域请求开始
        String origin = request.getHeader("Origin");
        if (StringUtils.isNotBlank(origin)) {
            res.setHeader("Access-Control-Allow-Origin", origin);
        }
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        String headers = request.getHeader("Access-Control-Request-Headers");
        if (StringUtils.isNotBlank(headers)) {
            res.setHeader("Access-Control-Allow-Headers", headers);
        }
        res.addHeader("Access-Control-Allow-Credentials", "true");
        //跨域请求结束
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if (allowedPath) {
            chain.doFilter(request, res);
        } else {
            //校验token
            try {
                tokenCheckUtil.parseToken(request.getHeader("token"));
            } catch (
                    Exception e
            ) {
                e.printStackTrace();
                dealErr(request, res, chain);
                return;
            }
            chain.doFilter(request, res);
        }
    }
    private void dealErr(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        res.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject obj = new JSONObject();
            obj.put("message", "token无效");
            obj.put("result", -1);
            obj.put("url", constantISCProperties.getLogInUrl() + "&service=" + constantISCProperties.getService() + "&renew=true");
            writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            System.out.println("token：" + constantISCProperties.getLogInUrl() + "service=" + constantISCProperties.getService());
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void destroy() {
        System.out.println("destroy----------filter");
    }
}
