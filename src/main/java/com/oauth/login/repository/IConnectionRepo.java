package com.oauth.login.repository;

import com.oauth.login.entity.UserEntity;

import java.util.Optional;

public interface IConnectionRepo {

        public Optional<UserEntity> getUserByEmail(String email);
}
