package com.oauth.login.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class UserSession {

    UUID userId;

    String authToken;

    String createdAt;
}
