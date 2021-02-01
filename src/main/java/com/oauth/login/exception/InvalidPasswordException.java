package com.oauth.login.exception;

public class InvalidPasswordException extends BusinessException{

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

}
