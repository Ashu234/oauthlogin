package com.oauth.login.service;

import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.User;
import com.oauth.login.domain.UserSession;
import com.oauth.login.entity.UserEntity;
import com.oauth.login.entity.UserSessionEntity;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.repository.IConnectionRepo;
import com.oauth.login.repository.IUserSessionRepo;
import com.oauth.login.util.DateUtils;
import com.oauth.login.util.PasswordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateRangeServiceTest {

    @Mock
    private DataService dataService;

    @Mock
    private IConnectionRepo iConnectionRepo;

    @Mock
    private IUserSessionRepo iUserSessionRepo;

    @Mock
    private PasswordUtils passwordUtils;

    @InjectMocks
    private DateRangeService dateRangeService;

    private List<UserSessionEntity> userSessionList;

    private UserSessionEntity userSession;

    private static final UUID TEST_USER_ID = UUID.randomUUID();
    private static final String TEST_AUTH_TOKEN = "00000000-0000-0000-0000-000000000000";
    private static final String TEST_CREATED_AT = "2021-01-02";
    private static final String FROM_TEST_DATE = "2021-02-01";
    private static final String TO_TEST_DATE = "2021-02-03";

    @Before
    public void setup() {

        userSession = new UserSessionEntity();
        userSessionList = new ArrayList<>();
    }

    @Test
    public void getUserSessionsWithinDateRange_success() {
        userSession.setUserId(TEST_USER_ID);
        userSession.setAuthToken(TEST_AUTH_TOKEN);
        userSession.setCreatedAt(DateUtils.ISOStringToInstant(DateUtils.instantTOISOString(Instant.now())));
        userSessionList.add(userSession);
        Optional<List<UserSessionEntity>> userSessionsEntityList = Optional.of(userSessionList);
        doReturn(userSessionsEntityList).when(iUserSessionRepo).findUserSessionInDateRange(any(), any());
        List<UserSession> resultList = dateRangeService.getUserSessionsWithinDateRange(FROM_TEST_DATE, TO_TEST_DATE);
        assertNotNull(resultList);
        assertEquals(resultList.get(0).getAuthToken(), TEST_AUTH_TOKEN);
    }



}















