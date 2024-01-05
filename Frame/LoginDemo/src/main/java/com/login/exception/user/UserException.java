package com.login.exception.user;

import com.login.exception.base.BaseException;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-25 10:06
 * @description: 用户信息异常类
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}