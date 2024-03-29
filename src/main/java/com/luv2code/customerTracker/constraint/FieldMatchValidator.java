package com.luv2code.customerTracker.constraint;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> { // annotation and the value to check
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {   // store annotation value to local
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try
        {
            final Object firstObj = new
                    BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new
                    BeanWrapperImpl(value).getPropertyValue(secondFieldName);
            valid = firstObj == null && secondObj == null || firstObj != null &&
                    firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            // we can ignore
        }
        if (!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
