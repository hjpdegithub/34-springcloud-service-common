package com.springboot.boot.utils;

public interface BeanInjector<F,T> {
    void inject(F from, T to);
}
