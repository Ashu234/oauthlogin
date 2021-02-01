package com.oauth.login.controller;

import com.oauth.login.domain.AuthToken;
import com.oauth.login.domain.User;
import com.oauth.login.exception.BadRequestException;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.exception.ErrorCodes;
import com.oauth.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/*
    Login Controller accepts user email and password
    It verifies both and return AuthToken
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateRangeController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "loginUser", response = AuthToken.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden Access or Wrong Password"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "System Error")
    })
    public ResponseEntity<AuthToken> loginUser(@Valid @RequestBody User userRequest) throws BusinessException{
        LOGGER.debug("User Login with information : {}", userRequest);
        //For Unit Testing
        if(StringUtils.isEmpty(userRequest.getEmail())){
            throw new BadRequestException("Please Provide Email", ErrorCodes.BAD_REQUEST_EXCEPTION);
        }

        if(StringUtils.isEmpty(userRequest.getPassword())){
            throw new BadRequestException("Please Provide Password ", ErrorCodes.BAD_REQUEST_EXCEPTION);
        }

        AuthToken token  = loginService.getToken(userRequest);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
