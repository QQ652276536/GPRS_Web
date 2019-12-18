package com.zistone.gprstest.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 普通类调用Spring bean对象：
 * 说明：此类需要放到同包或者子包下才能被扫描
 */
@Component
public class ServiceUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        if (ServiceUtil.applicationContext == null)
        {
            ServiceUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }

    public static <T> T GetBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T GetBean(String name, Class<T> clazz)
    {
        return getApplicationContext().getBean(name, clazz);
    }

}
