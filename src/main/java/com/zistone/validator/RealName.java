package com.zistone.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解实现类
@Constraint(validatedBy = RealNameValidator.class)
//注解应用类型
@Target({ElementType.METHOD, ElementType.FIELD})
//注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface RealName {
    String message() default "非法手机号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
