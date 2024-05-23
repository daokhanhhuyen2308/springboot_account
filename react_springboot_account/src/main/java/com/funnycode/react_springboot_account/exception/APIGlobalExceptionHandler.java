package com.funnycode.react_springboot_account.exception;

import com.funnycode.react_springboot_account.dto.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIGlobalExceptionHandler{

    @ExceptionHandler(CustomHandleException.class)
    public ResponseEntity<CustomError> handlerAccountException(CustomHandleException e){
        return new ResponseEntity<CustomError>(e.getCustomError() ,e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<String>(e.getMessage(), status);
    }

}
