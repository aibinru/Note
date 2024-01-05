package com.hspedu.seckill.vo;

import com.hspedu.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 韩顺平
 * @version 1.0
 * LoginVo: 接收用户登录时，发送的信息(mobile,password)
 */
@Data
public class LoginVo {
    //对LoginVo的属性值进行，约束
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
