package com.springboot.boot.common.redis;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.springboot.boot.annottion.RedisCache;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author mq
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(value = {"traffic-da.redis.cache.enable"}, matchIfMissing = true)
public class RedisCacheAspect {

    @Value("${traffic-da.redis.cache.timeout}")
    private Integer timeout;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Pointcut("@annotation(com.springboot.boot.annottion.RedisCache)")
    public void setJoinPoint() {
    }

    /**
     * 环绕通知:可以获取返回值
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "setJoinPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RequestAttributes ra= RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra=(ServletRequestAttributes) ra;
        String url= sra.getRequest().getRequestURI();
        Object result = null;
        String key = getCacheKey(proceedingJoinPoint,url);
        try {
            if (redisCacheUtil.getCacheObject(key) != null) {
                result = redisCacheUtil.getCacheObject(key);
                return JSON.parseObject(result.toString(), ApiResult.class);
            }
        } catch (Throwable e) {
            log.error("查询缓存异常!!!:{}", e.getMessage());
        }

            result = proceedingJoinPoint.proceed();

        try {
            if (result instanceof ApiResult) {
                ApiResult api = (ApiResult) result;
                redisCacheUtil.setCacheObject(key, JSON.toJSONStringWithDateFormat(api, JSON.DEFFAULT_DATE_FORMAT), getCacheExpires(proceedingJoinPoint),
                        TimeUnit.MINUTES);
            }
        } catch (Throwable e) {
            log.error("设置缓存异常!!!:{}", e.getMessage());
        }
        return result;
    }

    private Integer getCacheExpires(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = ms.getMethod();
        int expires = method.getAnnotation(RedisCache.class).expires();
        if (expires <= 0) {
            return timeout;
        }
        return expires;
    }

    @SuppressWarnings("unused")
    private String getCacheKey(ProceedingJoinPoint joinPoint,String url) {
        //获取目标方法参数
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            url = url + "@" + StrUtil.join(",",args);
        }
        return url;
    }

}