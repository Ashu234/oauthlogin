package com.oauth.login.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserEntity {

    UUID id;
    String email;
    String password;
}
