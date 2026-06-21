package com.eg.yafi.resp;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResp {
    public final String message;
    public final int status;
    public final LocalDateTime dateTime;

    public ErrorResp(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.dateTime = LocalDateTime.now();
    }
}
