package com.example.architecture.impl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {


    private final HttpStatus status;
    private final ExceptionModel exception;




}
