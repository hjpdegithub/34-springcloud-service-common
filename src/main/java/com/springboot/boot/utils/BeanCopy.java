package com.springboot.boot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BeanCopy {
    public static <F,T> T copy(F from,T to){
        return copy(from,to,null);
    }
    public static <F,T> T copy(F from,T to,BeanInjector<F,T> injector){
        if(from ==null){
            return null;
        }

        org.springframework.cglib.beans.BeanCopier beanCopier=
                org.springframework.cglib.beans.BeanCopier.create(from.getClass(),to.getClass(),false);
        beanCopier.copy(from, to,null);
        if(injector!=null) {
            injector.inject(from,to);
        }
        return to;
    }

    public static <F,T> List<T> copyList(Class<F> from, Class<T> to, List<F> data){
        return copyList(from,to,data,null);
    }

    public static <F,T> List<T> copyList(Class<F> from,Class<T> to,List<F> data,BeanInjector<F,T> injector){
        if(data==null || data.size()==0){
            return Collections.emptyList();
        }
        org.springframework.cglib.beans.BeanCopier beanCopier=
                org.springframework.cglib.beans.BeanCopier.create(from,to,false);
        List<T> result =new ArrayList<>();
        try {
            for(F each:data) {
                T toInstance = to.newInstance();
                beanCopier.copy(each,toInstance , null);
                if(injector!=null){
                    injector.inject(each,toInstance);
                }
                result.add(toInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}
