package com.oauth.login.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthToken {
    UUID authToken;

    public AuthToken(UUID authToken) {
        this.authToken = authToken;
    }
}
