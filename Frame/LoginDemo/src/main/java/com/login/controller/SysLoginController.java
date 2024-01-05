package com.login.controller;

import com.login.constant.Constants;
import com.login.model.LoginBody;
import com.login.service.impl.SysUserLoginService;
import com.login.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description:
 */
@RestController
public class SysLoginController {

    @Autowired
    SysUserLoginService sysUserLoginService;

    /**
     * 登录验证
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody) {
        R r = R.success();
        String token = sysUserLoginService.login(loginBody.getUserName(), loginBody.getPassword(),
                loginBody.getCode(), loginBody.getUuid());
        r.put(Constants.TOKEN, token);
        return r;
    }

    @GetMapping("/get")
    public String get(){
        System.out.println("get");
        return "get";
    }
}
