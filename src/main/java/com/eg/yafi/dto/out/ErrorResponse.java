package com.eg.yafi.dto.out;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    public LocalDateTime dateTime;
    public String message;
    public int status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.dateTime = LocalDateTime.now();
    }
}
