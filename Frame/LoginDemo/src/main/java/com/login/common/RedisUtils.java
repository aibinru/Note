package com.login.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 16:01
 * @description: Redis工具类
 */
@Component
public class RedisUtils {

    @Autowired
    public RedisTemplate redisTemplate;

    public <T> void setCacheObject(final String key,final T value){
        redisTemplate.opsForValue().set(key,value);
    }

    public <T> T getCacheObject(final String key){
        ValueOperations<String,T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

}
