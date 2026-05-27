package com.eduhub.eduhub_backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourseNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),"NOT_FOUND",
                ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
