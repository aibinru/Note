package com.hspedu.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.seckill.mapper.GoodsMapper;
import com.hspedu.seckill.mapper.SeckillGoodsMapper;
import com.hspedu.seckill.pojo.Goods;
import com.hspedu.seckill.pojo.SeckillGoods;
import com.hspedu.seckill.service.GoodsService;
import com.hspedu.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class SeckillGoodsServiceImpl
        extends ServiceImpl<SeckillGoodsMapper, SeckillGoods>
        implements SeckillGoodsService {
}
