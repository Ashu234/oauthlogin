package com.oauth.login.validation;

import com.oauth.login.domain.DateRange;
import com.oauth.login.util.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/*
  Date Validator is used to validate the format and correct Calendar Date
 */
public class DateValidator implements ConstraintValidator<ValidDate, Object> {

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext constraintValidatorContext) {

        final DateRange dateRange = (DateRange) obj;
        if(DateUtils.isValid(dateRange.getFromDate()) && DateUtils.isValid(dateRange.getToDate())) {
            return true;
        }
        return false;
    }
}
