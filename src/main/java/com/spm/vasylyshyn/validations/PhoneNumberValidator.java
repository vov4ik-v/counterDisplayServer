package com.spm.vasylyshyn.validations;

import com.spm.vasylyshyn.annotations.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber,String> {
    private static String regexPatternWithCountry = "\\+380\\d{9}";
    private static String regexPatternWithoutCountry = "0\\d{9}";
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return validatePhoneNumber(phoneNumber);
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        Pattern patternWithCountry = Pattern.compile(regexPatternWithCountry);
        Pattern patternWithoutCountry = Pattern.compile(regexPatternWithoutCountry);
        Matcher matcherWithCountry = patternWithCountry.matcher(phoneNumber);
        Matcher matcherWithoutCountry = patternWithoutCountry.matcher(phoneNumber);
        return matcherWithoutCountry.matches() || matcherWithCountry.matches();
    }
}
