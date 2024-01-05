package com.hspedu.seckill.controller;

import com.hspedu.seckill.pojo.User;
import com.hspedu.seckill.service.UserService;
import com.hspedu.seckill.util.CookieUtil;
import com.hspedu.seckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {


    //装配UserSerivce
    @Resource
    private UserService userService;


    //方法: 返回登录用户的信息, 同时介绍请求携带参数address
    //@RequestMapping("/info")
    //@ResponseBody
    //public RespBean info(User user, String address) {
    //    System.out.println("address->" + address);
    //    return RespBean.success(user);
    //}



    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }


    //方法:处理更新密码
    @RequestMapping("/updpwd")
    @ResponseBody
    public RespBean updatePasword(String userTicket,
                                  String password,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {



        return userService.updatePassword(userTicket,password,request,response);

    }
}
