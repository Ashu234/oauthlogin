package com.oauth.login.service;

import com.oauth.login.controller.DateRangeController;
import com.oauth.login.domain.UserSession;
import com.oauth.login.entity.UserSessionEntity;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.repository.IUserSessionRepo;
import com.oauth.login.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DateRangeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateRangeController.class);

    @Autowired
    private IUserSessionRepo iUserSessionRepo;

    public DateRangeService( ) {

    }

    public List<UserSession> getUserSessionsWithinDateRange(String from, String to) {
        List<UserSession> userSessionsList = null;
        try {

            Optional<List<UserSessionEntity>> userSessionsEntityList = iUserSessionRepo.findUserSessionInDateRange(from, to);
            if (userSessionsEntityList.isPresent()) {
                userSessionsList = userSessionsEntityList.get().stream().map(use -> getUserSession(use)).collect(Collectors.toList());

            }
            return userSessionsList;
        }
        catch (Exception e) {
            LOGGER.warn("Unable to get Session within Date Range", e);
            throw new BusinessException(e.getMessage());
        }
    }

    private UserSession getUserSession(UserSessionEntity userSessionEntity) {
        UserSession userSession = new UserSession();
        userSession.setUserId(userSessionEntity.getUserId());
        userSession.setAuthToken(userSessionEntity.getAuthToken());
        userSession.setCreatedAt(DateUtils.instantTOISOString(userSessionEntity.getCreatedAt()));
        return userSession;
    }
}
