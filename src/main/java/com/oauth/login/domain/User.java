package com.oauth.login.domain;

import com.oauth.login.validation.ValidEmail;
import com.oauth.login.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class User {

    UUID id;

    @ValidEmail
    @NotNull
    @NotBlank(message = "Email is mandatory")
    String email;

    @NotNull
    @NotBlank(message = "Password is mandatory")
    @ValidPassword
    String password;

}
