package com.saviobandeira.estoque.controllers.handlers;

import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;
import com.saviobandeira.estoque.dto.CustomError;

import java.time.Instant;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }
}
