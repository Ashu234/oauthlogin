package com.oauth.login.service;

import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.User;
import com.oauth.login.entity.UserEntity;
import com.oauth.login.entity.UserSessionEntity;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.repository.IConnectionRepo;
import com.oauth.login.repository.IUserSessionRepo;
import com.oauth.login.util.PasswordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private DataService dataService;

    @Mock
    private IConnectionRepo iConnectionRepo;

    @Mock
    private IUserSessionRepo iUserSessionRepo;

    @Mock
    private PasswordUtils passwordUtils;

    @InjectMocks
    private LoginService loginService;

    private UserEntity userEntity;

    private User user;

    private static final String TEST_EMAIL = "edwin@cerebri.com";
    private static final String TEST_PASSWORD = "jbcv42016@@%$JGT09";

    @Before
    public void setup() {
        userEntity = new UserEntity();
        user = new User();
    }

    @Test(expected = BusinessException.class)
    public void getToken_NotMatchingBcryptPassword() {
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        doReturn(iConnectionRepo).when(dataService).getFileRepo();
        Optional<UserEntity> userEntity = Optional.of(new UserEntity());
        userEntity.get().setEmail(TEST_EMAIL);
        userEntity.get().setPassword(TEST_PASSWORD);
        doReturn(userEntity).when(iConnectionRepo).getUserByEmail(any());
        when(iUserSessionRepo.save(Mockito.any(UserSessionEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        AuthToken result = loginService.getToken(user);
        assertNotNull(result);
    }


}















