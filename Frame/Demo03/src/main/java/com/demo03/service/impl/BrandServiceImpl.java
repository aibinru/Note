package com.demo03.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo03.dao.BrandDao;
import com.demo03.entity.BrandEntity;
import com.demo03.service.BrandService;
import org.springframework.stereotype.Service;

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

}
