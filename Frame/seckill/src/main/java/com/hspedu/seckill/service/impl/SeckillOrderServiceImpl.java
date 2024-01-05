package com.hspedu.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.seckill.mapper.OrderMapper;
import com.hspedu.seckill.mapper.SeckillOrderMapper;
import com.hspedu.seckill.pojo.Order;
import com.hspedu.seckill.pojo.SeckillOrder;
import com.hspedu.seckill.service.OrderService;
import com.hspedu.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Service;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class SeckillOrderServiceImpl
        extends ServiceImpl<SeckillOrderMapper, SeckillOrder>
        implements SeckillOrderService {
}
