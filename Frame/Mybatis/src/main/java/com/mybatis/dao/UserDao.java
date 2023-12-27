package com.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-27 14:27
 * @description:
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    //根据名称查询
    public UserEntity queryByNamess(String name);

}
