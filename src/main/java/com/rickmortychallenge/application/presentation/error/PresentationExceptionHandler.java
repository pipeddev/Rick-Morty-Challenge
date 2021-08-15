package com.rickmortychallenge.application.presentation.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PresentationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    void handleValidationError(Exception ex, HttpServletResponse response) throws Exception {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getCause().getLocalizedMessage());
    }
}
