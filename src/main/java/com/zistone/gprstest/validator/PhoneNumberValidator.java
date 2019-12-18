package com.zistone.gprstest.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private Pattern pattern = Pattern.compile("^(13|14|15|18|17)[0-9]{9}");

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(value).matches();
    }
}
