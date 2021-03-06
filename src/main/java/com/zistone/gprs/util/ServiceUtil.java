package com.zistone.gprs.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 普通类调用Spring Bean对象的工具类
 * 说明:此类需要放到同包或者子包下才能被扫描
 */
@Component
public final class ServiceUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**
     * （禁止外部实例化）
     */
    private ServiceUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ServiceUtil.applicationContext == null) {
            ServiceUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object GetBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T GetBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T GetBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
