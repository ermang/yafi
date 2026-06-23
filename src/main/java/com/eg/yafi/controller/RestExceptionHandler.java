package com.eg.yafi.controller;

import com.eg.yafi.resp.ErrorResp;
import com.eg.yafi.util.Constant;
import com.eg.yafi.util.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResp defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(e.getMessage(), e);
        return new ErrorResp(Constant.OOPS_SOMETHING_UNEXPECTED_HAPPENED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { RuntimeException.class })
    protected ErrorResp handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorResp(Constant.OOPS_SOMETHING_UNEXPECTED_HAPPENED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ErrorResp handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {

        return new ErrorResp(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ErrorResp handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {

        return new ErrorResp(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { UnAuthorizedException.class })
    protected ErrorResp handleUnAuthorizedException(UnAuthorizedException ex, WebRequest request) {

        return new ErrorResp(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ErrorResp handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        String validationErrors = String.join(", ",
                ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));

        return new ErrorResp(validationErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    protected ErrorResp handleUnAuthorizedException(HttpMessageNotReadableException ex, WebRequest request) {

        return new ErrorResp(ex.getMessage());
    }
}
