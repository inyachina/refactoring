package com.example.springbackend.facade;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorBody {

    private final String errorMessage;

    @JsonIgnore
    private final String trace;

    @JsonIgnore
    private final HttpStatus httpStatus;

    public ErrorBody(String errorMessage,  HttpStatus httpStatus, String trace) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.trace = trace;
    }
    public ErrorBody(String errorMessage,  HttpStatus httpStatus ) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.trace = null;
    }
}

