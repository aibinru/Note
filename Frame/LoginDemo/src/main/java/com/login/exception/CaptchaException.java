package com.login.exception;

import com.login.exception.user.UserException;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-25 10:09
 * @description: 验证码错误异常类
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}