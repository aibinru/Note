package com.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis.dao.UserDao;
import com.mybatis.entity.UserEntity;
import com.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-27 14:29
 * @description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {



    @Override
    public UserEntity name(String name) {
        UserEntity user =  baseMapper.queryByNamess(name);

        System.out.println(user);
        return user;
    }
}
