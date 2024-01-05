package com.mybatis.controller;

import com.mybatis.entity.UserEntity;
import com.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-27 14:56
 * @description:
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/query")
    public UserEntity queryUser(){
        return userService.getById(1);
    }

    @RequestMapping("/username")
    public UserEntity queryByName(@RequestBody String name){
        return userService.name(name);
    }

    @RequestMapping("/hi")
    public String hello(){
        return "hello";
    }


}
