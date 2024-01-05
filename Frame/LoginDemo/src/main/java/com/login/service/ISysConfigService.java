package com.login.service;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description: 参数配置 服务
 */
public interface ISysConfigService {

    /**
     * 获取验证码开关
     * @return
     */
    public boolean selectCaptchaEnabled();

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数key
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey);
}
