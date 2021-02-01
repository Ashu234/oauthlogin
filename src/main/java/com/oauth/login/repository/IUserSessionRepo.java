package com.oauth.login.repository;

import com.oauth.login.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface IUserSessionRepo extends CrudRepository<UserSessionEntity, Long>, QueryByExampleExecutor<UserSessionEntity>{

    @Query(value = "select * from user_session_entity where created_at between ?1 and ?2", nativeQuery = true)
    Optional<List<UserSessionEntity>> findUserSessionInDateRange(String fromDate, String toDate);
}
