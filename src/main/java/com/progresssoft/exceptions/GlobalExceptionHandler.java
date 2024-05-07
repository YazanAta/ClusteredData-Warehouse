package com.progresssoft.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        logger.error(String.valueOf(errors));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDealsFoundException.class)
    public ResponseEntity<?> handleNoDealsFoundException(NoDealsFoundException ex) {
        logger.warn("No Deals Found");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DealNotFoundException.class)
    public ResponseEntity<String> handleDealNotFoundException(DealNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
