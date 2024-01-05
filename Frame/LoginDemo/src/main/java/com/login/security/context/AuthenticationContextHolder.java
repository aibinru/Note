package com.login.security.context;

import org.springframework.security.core.Authentication;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description: 身份验证信息
 */
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext(){
        return contextHolder.get();
    }

    public static void setContext(Authentication context){
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }

}
