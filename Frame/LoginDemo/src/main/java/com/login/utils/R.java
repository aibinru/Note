package com.login.utils;

import com.login.constant.HttpStatus;

import java.util.HashMap;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:49
 * @description:
 */

public class R extends HashMap<String, Object> {
    private static final Long serialsVersionUID = 1L;

    private static final String CODE_TAG = "code";
    private static final String MSG_TAG = "msg";
    private static final String DATA_TAG = "data";

    public R(){

    }

    public R(int code,String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public R(int code,String msg,Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if(StringUtils.isNotNull(data)){
            super.put(DATA_TAG, data);
        }
    }

    public static R success(){
        return R.success("操作成功");
    }

    public static R success(Object data){
        return R.success("操作成功",data);
    }

    public static R success(String msg){
        return R.success(msg,"");
    }

    public static R success(String msg,Object data){
        return new R(HttpStatus.SUCCESS,msg,data);
    }

    public static R error(){
        return R.success("操作失败");
    }

    public static R error(String msg){
        return R.error(msg,"");
    }

    public static R error(int code,String msg){
        return new R(code,msg);
    }

    public static R error(String msg,Object data){
        return new R(HttpStatus.ERROR,msg,data);
    }


    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public R put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
