package com.moutii.TheaterHub.handler;

import com.moutii.TheaterHub.exception.BusinessException;
import com.moutii.TheaterHub.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handler(BusinessException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(res);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(UsernameNotFoundException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ErrorCode.USERNAME_NOT_FOUND.getCode())
                .message(ErrorCode.USERNAME_NOT_FOUND.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.USERNAME_NOT_FOUND.getStatus()).body(res);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(EntityNotFoundException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code("ENTITY_NOT_FOUND")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handler(BadCredentialsException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ErrorCode.BAD_CREDENTIALS.getCode())
                .message(ErrorCode.BAD_CREDENTIALS.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.BAD_CREDENTIALS.getStatus()).body(res);
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handler(AuthorizationDeniedException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ErrorCode.AUTHORIZATION_DENIED.getCode())
                .message(ErrorCode.AUTHORIZATION_DENIED.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.AUTHORIZATION_DENIED.getStatus()).body(res);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handler(DisabledException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ErrorCode.USER_DISABLED.getCode())
                .message(ErrorCode.USER_DISABLED.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.USER_DISABLED.getStatus()).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handler(MethodArgumentNotValidException ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .validationErrors(
                        ex.getBindingResult().getAllErrors().stream()
                                .map(err -> {
                                    final String message = err.getDefaultMessage();
                                    final String code = err.getDefaultMessage();
                                    final String fieldName = ((FieldError) err).getField();
                                    return ErrorResponse.ValidationError.builder()
                                            .code(code)
                                            .message(message)
                                            .fieldName(fieldName)
                                            .build();
                                })
                                .toList()
                )
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handler(Exception ex) {
        log.debug(ex.getMessage(),ex);
        final ErrorResponse res = ErrorResponse.builder()
                .code(ErrorCode.INTERNAL_EXCEPTION.getCode())
                .message(ErrorCode.INTERNAL_EXCEPTION.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.INTERNAL_EXCEPTION.getStatus()).body(res);
    }



}
