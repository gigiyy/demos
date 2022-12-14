package com.example.fcb.core;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@ToString
public class ApiError {

    private HttpStatus status;
    private String message;
}
