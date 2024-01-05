package com.demo03.utils;

import com.demo03.constant.HttpStatus;

import java.util.HashMap;

/**
 * ruoyi和人人fast的有什么不同
 * 1、renren-fast 把new对象和给变量赋值的操作放到了普通方法里
 * 2、写了一个单独放数据的方法
 *
 * ruoyi       把new对象和给变量赋值的操作放到了构造方法里
 *
 *
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final String CODE_TAG = "code";

    private static final String MSG_TAG = "msg";

    private static final String DATA_TAG = "data";

    private int code;

    public R(){

    }

    public R(int code,String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG,msg);
    }

    public R(int code,String msg,Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if(StringUtils.isNotNull(data)){
            super.put(DATA_TAG,data);
        }
    }


    //不需要传入
    public static R success(){
        return R.success("操作成功!");
    }

    //传入数据
    public static R success(Object data){
        return R.success("操作成功!",data);
    }

    //传入消息
    public static R success(String msg){
        return  R.success(msg,"");
    }

    //传入信息和数据
    public static R success(String msg,Object data){
        return new R(HttpStatus.SUCCESS,msg,data);
    }

    //错误返回
    public static R error(){
        return R.error("操作失败!");
    }

    public static R error(String msg){
        return R.error(msg,"");//调用自己的方法
    }

    public static R error(int code,String msg){
        return new R(code,msg);
    }

    public static R error(String msg,Object data){
        return new R(HttpStatus.ERROR,msg,data);
    }

//    //返回错误状态码
//    public static R error(int code,String msg,Object data){
//        return new R(code,msg,data);
//    }


}
