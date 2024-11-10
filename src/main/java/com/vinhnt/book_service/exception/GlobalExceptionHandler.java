package com.vinhnt.book_service.exception;


import com.vinhnt.book_service.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponse<Object> response = ApiResponse.failureData("Validation failed", "VALIDATION_FAILED", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse> handleBookNotFoundException(BookNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.failure(ex.getMessage(), "BOOK_NOT_FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ///TODO: MESSAGE HERE IS A HARDCODED STRING. IT SHOULD BE DYNAMIC AND BASED ON THE EXCEPTION
        String errorMessage          = "A book with this ISBN already exists.";
        ApiResponse<Object> response = ApiResponse.failure(errorMessage, "DATA_INTEGRITY_VIOLATION");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}