package com.curtisnewbie.constraints;

import com.curtisnewbie.qualifiers.PostCodePattern;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostCodeValidator implements ConstraintValidator<PostCode, String> {

    @Inject
    @PostCodePattern
    Pattern postcodePattern;

    @Override
    public void initialize(PostCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // always consider null as valid as recommended
        if(value == null){
            return true;
        }

        Matcher m = postcodePattern.matcher(value);
        if(!m.matches())
            return false;
        else
            return true;
    }
}
