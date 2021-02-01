package com.oauth.login.exception;

public class BadRequestException extends BusinessException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode.getCode();
    }
}
