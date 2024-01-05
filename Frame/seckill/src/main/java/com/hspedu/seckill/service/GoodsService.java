package com.hspedu.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.seckill.pojo.Goods;
import com.hspedu.seckill.pojo.User;
import com.hspedu.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface GoodsService extends IService<Goods> {
    //秒杀商品列表
    List<GoodsVo> findGoodsVo();
    //获取商品详情-根据goodsId
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
