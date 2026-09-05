package com.moutii.TheaterHub.handler;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String code;

    private String message;

    private List<ValidationError> validationErrors;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class ValidationError {
        private String code;
        private String message;
        private String fieldName;
    }
}
