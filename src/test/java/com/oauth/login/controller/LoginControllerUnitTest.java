package com.oauth.login.controller;

import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.User;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerUnitTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private User user;

    private static final String TEST_EMAIL = "henry@cerebri.com";
    private static final String TEST_PASSWORD = "09OLFTxvcEUN$re";

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void loginUser_success() {
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        AuthToken authToken = new AuthToken(UUID.randomUUID());
        when(loginService.getToken(any())).thenReturn(authToken);
        ResponseEntity<AuthToken> response = loginController.loginUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authToken.getAuthToken(), response.getBody().getAuthToken());
    }

    @Test(expected = BusinessException.class)
    public void loginUser_badRequestEmail() {
        user.setPassword(TEST_PASSWORD);
        ResponseEntity<AuthToken> response = loginController.loginUser(user);
    }

    @Test(expected = BusinessException.class)
    public void loginUser_badRequestPassword() {
        user.setEmail(TEST_EMAIL);
        ResponseEntity<AuthToken> response = loginController.loginUser(user);
    }

}









