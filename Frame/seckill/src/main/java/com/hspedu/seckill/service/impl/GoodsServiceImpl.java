package com.hspedu.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.seckill.mapper.GoodsMapper;
import com.hspedu.seckill.mapper.UserMapper;
import com.hspedu.seckill.pojo.Goods;
import com.hspedu.seckill.pojo.User;
import com.hspedu.seckill.service.GoodsService;
import com.hspedu.seckill.service.UserService;
import com.hspedu.seckill.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class GoodsServiceImpl
        extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    //装配GoodsMapper
    @Resource
    private  GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    //根据商品id,返回对应秒杀商品的详情
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
