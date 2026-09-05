package com.moutii.TheaterHub.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS","A user with this email already exists",HttpStatus.BAD_REQUEST),
    PHONE_ALREADY_EXISTS("PHONE_ALREADY_EXISTS","A user with this phone number already exists" ,HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH","password and password confirmation are not equal" ,HttpStatus.BAD_REQUEST ),
    NOT_OLD_ENOUGH("NOT_OLD_ENOUGH","Age must be greater or equal to 18" ,HttpStatus.BAD_REQUEST )
    ;


    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
