package com.funnycode.react_springboot_account.exception;

import com.funnycode.react_springboot_account.dto.CustomError;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomHandleException extends RuntimeException{

    HttpStatus httpStatus;
    CustomError customError;

    public static CustomHandleException notFountException(String message){
        return CustomHandleException.builder()
                .customError(CustomError.builder().code("404").message(message).build())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

    public static CustomHandleException badRequestException(String message){
        return CustomHandleException.builder()
                .customError(CustomError.builder().code("400").message(message).build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }

    public static CustomHandleException unauthorizedException(String message){
        return CustomHandleException.builder()
                .customError(CustomError.builder().code("401").message(message).build())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
    }
}
