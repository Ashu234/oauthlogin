package com.oauth.login.controller;

import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.DateRange;
import com.oauth.login.domain.User;
import com.oauth.login.domain.UserSession;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.service.DateRangeService;
import com.oauth.login.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateRangeControllerUnitTest {

    @Mock
    private DateRangeService dateRangeService;

    @InjectMocks
    private DateRangeController dateRangeController;

    private DateRange dateRange;
    private List<UserSession> userSessionEntityList;
    private UserSession userSession;

    private static final String FROM_TEST_DATE = "2021-02-01";
    private static final String TO_TEST_DATE = "2021-02-03";

    @Before
    public void setup() {
        dateRange = new DateRange();
        userSession = new UserSession();
        userSessionEntityList = new ArrayList<>();
    }

    @Test
    public void getUserSessionsInDateRange_success() {
        dateRange.setToDate(TO_TEST_DATE);
        dateRange.setFromDate(FROM_TEST_DATE);
        userSessionEntityList.add(userSession);
        when(dateRangeService.getUserSessionsWithinDateRange(any(), any())).thenReturn(userSessionEntityList);
        ResponseEntity<List<UserSession>> response = dateRangeController.getUserSessionsInDateRange(dateRange);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test(expected = BusinessException.class)
    public void getUserSessionsInDateRange_badRequestToDate() {
        dateRange.setFromDate(FROM_TEST_DATE);
        ResponseEntity<List<UserSession>> response = dateRangeController.getUserSessionsInDateRange(dateRange);
    }

    @Test(expected = BusinessException.class)
    public void getUserSessionsInDateRange_badRequestFromDate() {
        dateRange.setToDate(TO_TEST_DATE);
        ResponseEntity<List<UserSession>> response = dateRangeController.getUserSessionsInDateRange(dateRange);
    }

}









