package com.eg.yafi.dto.out;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    public final String message;
    public final int status;
    public final LocalDateTime dateTime;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.dateTime = LocalDateTime.now();
    }
}
