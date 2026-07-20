package com.eg.yafi.controller;

import com.eg.yafi.resp.ErrorResp;
import com.eg.yafi.util.UnAuthorizedException;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ErrorResp defaultErrorHandler(HttpServletRequest req, Exception e, Locale locale) throws Exception {
        logger.error(e.getMessage(), e);

        String localizedMessage = messageSource.getMessage("service.oops", null, locale);
        return new ErrorResp(localizedMessage);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ErrorResp handleNoSuchElementException(NoSuchElementException ex, Locale locale) {

        logger.error(ex.getMessage(), ex);
        String localizedMessage =  messageSource.getMessage(ex.getMessage(), null, locale);

        return new ErrorResp(localizedMessage);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntityExistsException.class)
    public ErrorResp handleEntityExistsException(EntityExistsException e, Locale locale) throws Exception {

       logger.error(e.getMessage(), e);
       String localizedMessage =  messageSource.getMessage(e.getMessage(), null, locale);

        return new ErrorResp(localizedMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadCredentialsException.class)
    public ErrorResp handleBadCredentialsException(BadCredentialsException e, Locale locale) throws Exception {

        logger.error(e.getMessage(), e);
        String localizedMessage = messageSource.getMessage("request.validation.bad.credentials", null, locale);

        return new ErrorResp(localizedMessage);
    }
}
