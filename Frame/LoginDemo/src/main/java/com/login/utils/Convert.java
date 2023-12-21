package com.login.utils;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:41
 * @description:类型转换器
 */
public class Convert {

    /**
     *
     * @param value
     * @return
     */
    public static String toStr(Object value){
        return toStr(value,null);
    }

    public static String toStr(Object value,String defaultValue){
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    public static Boolean toBool(Object value) {
        return toBool(value,null);
    }

    public static Boolean toBool(Object value,Boolean defalutValue){
        return true;
    }


}
