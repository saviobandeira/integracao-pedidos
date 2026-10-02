package com.saviobandeira.estoque.controllers.handlers;

import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;
import com.saviobandeira.estoque.dto.CustomError;
import com.saviobandeira.estoque.dto.ValidationError;
import com.saviobandeira.estoque.services.exceptions.DuplicateResourceException;
import com.saviobandeira.estoque.services.exceptions.InsufficientBalanceException;

import java.time.Instant;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        ValidationError validationError = new ValidationError(Instant.now(), status.value(), "Dados invalidos", request.getRequestURI());
        for (FieldError fieldError : error.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<CustomError> duplicateResource(DuplicateResourceException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError customError = new CustomError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomError> dataIntegrityViolation(DataIntegrityViolationException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError customError = new CustomError(Instant.now(), status.value(), "A operação viola uma restrição de integridade dos dados", request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomError> insufficientBalance(InsufficientBalanceException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        CustomError customError = new CustomError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

}
