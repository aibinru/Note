package com.hspedu.seckill.validator;

import com.hspedu.seckill.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
//我们自拟定注解 IsMobile   的校验规则, 可以自己根据业务需求来编写..
public class IsMobileValidator
        implements ConstraintValidator<IsMobile, String> {
    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        //初始化
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //必填
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {//非必填
            if (!StringUtils.hasText(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
