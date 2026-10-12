package com.chirag.bookmyshowbackend.controller;


import com.chirag.bookmyshowbackend.dto.ApiError;
import com.chirag.bookmyshowbackend.service.ProfileConflictException;
import com.chirag.bookmyshowbackend.service.ResourceNotFoundException;
import com.chirag.bookmyshowbackend.service.SeatUnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class APIExceptionHandler {

    private ResponseEntity<ApiError> error (HttpStatus status, String message, HttpServletRequest request){
        return ResponseEntity.status(status).body(
                new ApiError(Instant.now(),status.value(),status.getReasonPhrase(),message,request.getRequestURI()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiError> notFound(ResourceNotFoundException exception, HttpServletRequest request){
        return error(HttpStatus.NOT_FOUND,exception.getMessage(),request);
    }

    @ExceptionHandler({SeatUnavailableException.class , IllegalArgumentException.class, ProfileConflictException.class})
    ResponseEntity<ApiError> conflict(RuntimeException exception, HttpServletRequest request){
        return error(HttpStatus.CONFLICT,exception.getMessage(),request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    ResponseEntity<ApiError> validation(Exception exception, HttpServletRequest request){
        return error(HttpStatus.BAD_REQUEST,"request validation failed",request);
    }

}
