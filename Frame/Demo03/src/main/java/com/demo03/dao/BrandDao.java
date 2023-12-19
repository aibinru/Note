package com.demo03.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo03.entity.BrandEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {

}
