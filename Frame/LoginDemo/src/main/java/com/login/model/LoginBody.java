package com.login.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description:
 */
@Data
public class LoginBody implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * uuid
     */
    private String uuid;
}
