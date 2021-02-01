package com.oauth.login.domain;

import com.oauth.login.validation.ValidDate;
import com.oauth.login.validation.ValidDateRange;
import lombok.Data;

@Data
@ValidDate
@ValidDateRange
public class DateRange {

    String fromDate;
    String toDate;
}
