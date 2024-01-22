package com.example.apptive19thhjfundbackend.user.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = RestApiException.class)
    public ResponseEntity<ErrorResponse> handleRestApiException(RestApiException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .status(e.getCode().getStatus())
                .build();
        log.error(errorResponse.toString() );
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
}