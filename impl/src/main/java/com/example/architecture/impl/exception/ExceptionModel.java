package com.example.architecture.impl.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@lombok.Data
@Builder
public class ExceptionModel {

    private HttpStatus status;
    private String name;
    private String message;
    private LocalDateTime timestamp;


}
