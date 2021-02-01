package com.oauth.login.service;

import com.oauth.login.controller.DateRangeController;
import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.User;
import com.oauth.login.entity.UserEntity;
import com.oauth.login.entity.UserSessionEntity;
import com.oauth.login.exception.ErrorCodes;
import com.oauth.login.exception.InvalidPasswordException;
import com.oauth.login.exception.ResourceNotFoundException;
import com.oauth.login.repository.IConnectionRepo;
import com.oauth.login.repository.IUserSessionRepo;
import com.oauth.login.util.DateUtils;
import com.oauth.login.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateRangeController.class);
    private DataService dataService;
    private IConnectionRepo iConnectionRepo;

    @Autowired
    private IUserSessionRepo iUserSessionRepo;

    public LoginService(DataService dataService) {
        this.dataService = dataService;
    }

    public AuthToken getToken(User userRequest) {
        iConnectionRepo = dataService.getFileRepo();
        Optional<UserEntity> user = iConnectionRepo.getUserByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            if (PasswordUtils.verifyPassword(userRequest.getPassword(), user.get().getPassword())) {

                UUID token = UUID.randomUUID();
                UserSessionEntity userSessionEntity = new UserSessionEntity();
                userSessionEntity.setUserId(user.get().getId());
                userSessionEntity.setAuthToken(token.toString());
                Instant time = DateUtils.ISOStringToInstant(DateUtils.instantTOISOString(Instant.now()));
                userSessionEntity.setCreatedAt(time);

                iUserSessionRepo.save(userSessionEntity);
                return new AuthToken(token);
            }
            throw new InvalidPasswordException(" Please enter a valid Password !!", ErrorCodes.INVALID_USERNAME_PASSWORD);
        } else {
            LOGGER.warn("Unable to find User");
            throw new ResourceNotFoundException("User with email not found !!", ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }
}
