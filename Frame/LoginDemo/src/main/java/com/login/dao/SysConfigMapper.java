package com.login.dao;

import com.login.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 16:27
 * @description:
 */
@Mapper
public interface SysConfigMapper {

    public SysConfigEntity selectConfig(SysConfigEntity configEntity);
}
