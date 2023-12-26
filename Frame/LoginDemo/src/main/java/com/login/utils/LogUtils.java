package com.login.utils;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-25 9:36
 * @description:
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
