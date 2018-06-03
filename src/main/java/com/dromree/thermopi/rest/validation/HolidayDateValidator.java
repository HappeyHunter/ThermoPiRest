package com.dromree.thermopi.rest.validation;

import com.dromree.thermopi.rest.data.HolidayData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HolidayDateValidator implements ConstraintValidator<HolidayDate, HolidayData> {

    @Override
    public void initialize(HolidayDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(HolidayData aHolidayData, ConstraintValidatorContext context) {
        if(aHolidayData.getStartDate() != null && aHolidayData.getEndDate() != null
            && aHolidayData.getEndDate().isBefore(aHolidayData.getStartDate())) {
            context.buildConstraintViolationWithTemplate("End Date cannot be before Start Date").addConstraintViolation();
            return false;
        }

        return true;
    }

}
