package org.clxmm.autocode.api.config.valid.pass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = PasswordValidatorClass.class)
public @interface PasswordValid {

    String[] value() default {};

    String message() default "密码不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
