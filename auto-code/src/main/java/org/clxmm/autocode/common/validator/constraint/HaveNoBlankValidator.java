package org.clxmm.autocode.common.validator.constraint;

import org.clxmm.autocode.common.validator.annotation.HaveNoBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HaveNoBlankValidator implements ConstraintValidator<HaveNoBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // null 不做检验
        if (value == null) {
            return true;
        }
        if (value.contains(" ")) {
            // 校验失败
            return false;
        }
        // 校验成功
        return true;
    }
}
