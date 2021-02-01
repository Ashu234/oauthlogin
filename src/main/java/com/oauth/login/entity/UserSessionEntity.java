package com.oauth.login.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
public class UserSessionEntity {

    @Id
    @GeneratedValue
    Integer id;

    UUID userId;

    String authToken;

    Instant createdAt;
}
