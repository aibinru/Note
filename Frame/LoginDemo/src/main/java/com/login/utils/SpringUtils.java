package com.login.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-22 9:43
 * @description:
 */
public class SpringUtils {

    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(String name) throws BeansException
    {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }
}
