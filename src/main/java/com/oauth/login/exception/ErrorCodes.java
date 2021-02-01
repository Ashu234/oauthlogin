package com.oauth.login.exception;

public enum ErrorCodes {
    INVALID_USERNAME_PASSWORD(403),
    RESOURCE_NOT_FOUND(404),
    BAD_REQUEST_EXCEPTION(400);

    private int code;

    ErrorCodes(int errorCode) {
        this.code = errorCode;
    }

    public Integer getCode() {
        return code;
    }
}
