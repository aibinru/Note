package com.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis.entity.UserEntity;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-27 14:30
 * @description:
 */
public interface UserService extends IService<UserEntity> {

    //根据名字查询
    public UserEntity name(String name);
}
