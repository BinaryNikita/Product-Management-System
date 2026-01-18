package com.example.productcrud.exception;

import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        var details = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ":" + fe.getDefaultMessage()).collect(Collectors.toList());

        var errors = new ApiError();
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setError("Validation Failed");
        errors.setMessage("One or more validation errors");
        errors.setDetails(details);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        var errors = new ApiError();
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setError("Resource not found ");
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiError> handleDb(DataAccessException ex) {
        var errors = new ApiError();
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errors.setError("Database error");
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResource(DuplicateResourceException ex) {
        var errors = new ApiError();
        errors.setStatus(HttpStatus.CONFLICT.value());
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex) {
        var errors = new ApiError();
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        errors.setError("Internal Server Error");
        errors.setMessage(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
