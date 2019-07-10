package com.zistone.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface UserName {
    String message() default "非法姓名";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
