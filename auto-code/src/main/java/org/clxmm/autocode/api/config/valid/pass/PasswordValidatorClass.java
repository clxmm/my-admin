package org.clxmm.autocode.api.config.valid.pass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidatorClass implements ConstraintValidator<PasswordValid,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        System.out.println(s);


        return false;
    }
}
