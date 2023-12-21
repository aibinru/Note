package com.login.service.impl;

import com.login.security.context.AuthenticationContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description:
 */
@Component
public class SysUserLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 随机数
     * @return token
     */
    public String login(String username,String password,String code,String uuid){

        //验证码校验
        validateCaptcha(username,code,uuid);

        //登录前置校验
        loginPreValidate(username,password);

        //用户校验
        validateUser(username,password);

        return "";
    }


    public void validateUser(String username,String password){
        //用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {

        } finally {
            AuthenticationContextHolder.clearContext();
        }
    }

    /**
     * 校验验证码
     * @param username 用户名
     * @param code 验证码
     * @param uuid 随机数
     */
    public void validateCaptcha(String username,String code,String uuid){

    }

    /**
     * 登录前置校验
     * @param username
     * @param password
     */
    public void loginPreValidate(String username,String password){

    }
}
