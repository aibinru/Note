package com.login.utils;

import static java.util.Objects.isNull;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description:
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断一个对象是否为null
     * @param ojb
     * @return
     */
    public static boolean isNotNull(Object ojb){
        return !isNull(ojb);
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

}
