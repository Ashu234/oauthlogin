package com.oauth.login.controller;

import com.oauth.login.exception.BadRequestException;
import com.oauth.login.exception.BusinessException;
import com.oauth.login.exception.InvalidPasswordException;
import com.oauth.login.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBusinessException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName(), exception.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName(), exception.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleBusinessException(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName(), exception.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> handleBusinessException(InvalidPasswordException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName(), exception.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
