package com.example.apptive19thhjfundbackend.user.common;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RestApiException.class)
    public ResponseEntity<ErrorResponse> handleRestApiException(RestApiException e) {
        LOGGER.info("[handleRestApiException] code: {}, message: {}, status: {}", e.getCode().getCode(), e.getMessage(),
                e.getCode().getStatus());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .status(e.getCode().getStatus())
                .build();
        log.error(errorResponse.toString());
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
}