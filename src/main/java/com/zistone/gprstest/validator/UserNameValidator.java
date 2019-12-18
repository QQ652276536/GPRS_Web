package com.zistone.gprstest.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private Pattern pattern = Pattern.compile("\\w{4,15}|[\\u4e00-\\u9fa5]{2,5}");

    @Override
    public void initialize(UserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(value).matches();
    }
}
