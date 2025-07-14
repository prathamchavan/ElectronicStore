package com.electronic.store.Electronic.Store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;
public class ImageNameValidator implements ConstraintValidator<ImageNameValid, String> {


    private Logger logger =LoggerFactory.getLogger(ImageNameValidator.class);
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        logger.info("Message from isValid : {}", s);

        //logic
        if(s.isBlank()){
        return false;
        }
        else
        {
            return true;
        }
    }
}
