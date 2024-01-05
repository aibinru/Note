package com.login.exception;

import com.login.exception.user.UserException;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-25 10:06
 * @description: 验证码失效异常类
 */
public class CaptchaExpireException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}