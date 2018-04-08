package com.github.comp354project.utils;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Object ob, Class<?> clazz) {
        if(ob.getClass().isAnnotationPresent(Timing.class)) {
            return(T)Proxy.newProxyInstance(ob.getClass().getClassLoader(),
                    new Class<?>[]{clazz}, new TimingInvocationHandler(ob));
        } else {
            return (T)ob;
        }
    }
}