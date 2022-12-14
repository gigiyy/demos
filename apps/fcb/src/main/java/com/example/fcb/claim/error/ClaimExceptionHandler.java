package com.example.fcb.claim.error;

import com.example.fcb.core.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ClaimExceptionHandler {

    @ExceptionHandler({ClaimNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(ClaimNotFoundException ex) {
        log.error(ex.getMessage());
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
