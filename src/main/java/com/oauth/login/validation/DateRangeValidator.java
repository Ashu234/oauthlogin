package com.oauth.login.validation;

import com.oauth.login.domain.DateRange;
import com.oauth.login.util.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext constraintValidatorContext) {

        final DateRange dateRange = (DateRange) obj;
        if(DateUtils.validateRange(dateRange.getFromDate(), dateRange.getToDate())) {
            return true;
        }
        return false;
    }
}
