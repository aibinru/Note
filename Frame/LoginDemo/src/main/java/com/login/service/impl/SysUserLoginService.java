package com.login.service.impl;

import com.login.cache.RedisCache;
import com.login.constant.CacheConstants;
import com.login.constant.Constants;
import com.login.constant.UserConstants;
import com.login.exception.*;
import com.login.manager.AsyncManager;
import com.login.manager.factory.AsyncFactory;
import com.login.security.context.AuthenticationContextHolder;
import com.login.service.ISysConfigService;
import com.login.utils.IpUtils;
import com.login.utils.MessageUtils;
import com.login.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    //@Autowired
    //private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;



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
        loginPreCheck(username,password);

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
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
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
//    public void validateCaptcha(String username,String code,String uuid){
//
//    }
//
//    /**
//     * 登录前置校验
//     * @param username
//     * @param password
//     */
//    public void loginPreValidate(String username,String password){
//
//    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            if (!code.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

//    /**
//     * 记录登录信息
//     *
//     * @param userId 用户ID
//     */
//    public void recordLoginInfo(Long userId)
//    {
//        SysUser sysUser = new SysUser();
//        sysUser.setUserId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr());
//        sysUser.setLoginDate(DateUtils.getNowDate());
//        userService.updateUserProfile(sysUser);
//    }
}
