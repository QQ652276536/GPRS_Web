package com.zistone.gprs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class RealNameValidator implements ConstraintValidator<RealName, String> {

    private Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]{2,4}$");

    @Override
    public void initialize(RealName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(value).matches();
    }
}
