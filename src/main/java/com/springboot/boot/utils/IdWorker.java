package com.springboot.boot.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 用于生成id的算法
 * 分布式id生成算法SnowFlake
 */
public class IdWorker {

    /**
     * 枚举类型是线程安全的，并且只会装载一次
     */
    private enum Singleton{
        /**
         * 单例
         */
        INSTANCE;

        private final Snowflake instance;

        Singleton(){
            instance = IdUtil.getSnowflake(1, 1);
        }

        private Snowflake getInstance(){
            return instance;
        }
    }

    public static Snowflake getInstance(){
      return  Singleton.INSTANCE.getInstance();
    }

}