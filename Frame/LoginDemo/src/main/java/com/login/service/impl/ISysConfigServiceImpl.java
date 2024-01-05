package com.login.service.impl;

import com.login.common.RedisUtils;
import com.login.constant.CacheConstants;
import com.login.dao.SysConfigMapper;
import com.login.entity.SysConfigEntity;
import com.login.service.ISysConfigService;
import com.login.utils.Convert;
import com.login.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description:
 */
@Service
public class ISysConfigServiceImpl implements ISysConfigService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    SysConfigMapper sysConfigMapper;

    /**
     * 获取验证码开关
     * @return
     */
    @Override
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.accoutn.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisUtils.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfigEntity config = new SysConfigEntity();
        config.setConfigKey(configKey);
        SysConfigEntity retConfig = sysConfigMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
            redisUtils.setCacheObject(getCacheKey(configKey),retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
