package com.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 24-1-5 10:05
 * @description:
 */
@RestController
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/hello")
    public String getValue(){
        System.out.println("hello");
        redisTemplate.opsForValue().set("hello","hello");
        return "hello";
    }
}
