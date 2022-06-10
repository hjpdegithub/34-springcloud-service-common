package com.springboot.boot.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap工具类
 */
public class Kv extends HashMap {
    public Kv() {
    }

    public static Kv by(Object key, Object value) {
        return new Kv().set(key, value);
    }

    public static Kv create() {
        return new Kv();
    }

    public Kv set(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public Kv setIfNotBlank(Object key, String value) {
        if (StrUtil.isNotBlank(value)) {
            set(key, value);
        }
        return this;
    }

    public Kv setIfNotNull(Object key, Object value) {
        if (value != null) {
            set(key, value);
        }
        return this;
    }

    public Kv set(Map map) {
        super.putAll(map);
        return this;
    }

    public Kv set(Kv kv) {
        super.putAll(kv);
        return this;
    }

    public Kv delete(Object key) {
        super.remove(key);
        return this;
    }

    public <T> T getAs(Object key) {
        return (T)get(key);
    }

    public String getStr(Object key) {
        Object s = get(key);
        return s != null ? s.toString() : null;
    }

    public Integer getInt(Object key) {
        Number n = (Number)get(key);
        return n != null ? n.intValue() : null;
    }

    public Long getLong(Object key) {
        Number n = (Number)get(key);
        return n != null ? n.longValue() : null;
    }

    public Double getDouble(Object key) {
        Number n = (Number)get(key);
        return n != null ? n.doubleValue() : null;
    }

    public Float getFloat(Object key) {
        Number n = (Number)get(key);
        return n != null ? n.floatValue() : null;
    }

    public Number getNumber(Object key) {
        return (Number)get(key);
    }

    public Boolean getBoolean(Object key) {
        return (Boolean)get(key);
    }

    /**
     * key 存在，并且 value 不为 null
     */
    public boolean notNull(Object key) {
        return get(key) != null;
    }

    /**
     * key 不存在，或者 key 存在但 value 为null
     */
    public boolean isNull(Object key) {
        return get(key) == null;
    }

    /**
     * key 存在，并且 value 为 true，则返回 true
     */
    public boolean isTrue(Object key) {
        Object value = get(key);
        return (value instanceof Boolean && ((Boolean)value == true));
    }

    /**
     * key 存在，并且 value 为 false，则返回 true
     */
    public boolean isFalse(Object key) {
        Object value = get(key);
        return (value instanceof Boolean && ((Boolean)value == false));
    }

    @Override
    public boolean equals(Object kv) {
        return kv instanceof Kv && super.equals(kv);
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
