package com.zistone.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private Pattern pattern = Pattern.compile("^(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}$");

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(value).matches();
    }
}
