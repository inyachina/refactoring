package com.example.springbackend.controller;

import com.example.springbackend.data.ExceptionResponse;
import com.example.springbackend.exception.ApiException;
import com.example.springbackend.exception.BadRequestException;
import com.example.springbackend.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException e) {
        String message = (e.getMessage() != null) ? e.getMessage() : e.getDefaultMessage();
        log.error("Api exception handler {}: {}", e.getClass().getName(), message);
        return ResponseEntity
                .status(e.getStatus())
                .body(new ExceptionResponse(e.getClass().getName(), message));
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            PropertyReferenceException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity toMapBadRequestException(Exception e) {
        return this.handleApiException(new BadRequestException());
    }

    @ExceptionHandler({
            EmptyResultDataAccessException.class,
    })
    public ResponseEntity toMapNoEntityException(Exception e) {
        return this.handleApiException(new NotFoundException());
    }
}
