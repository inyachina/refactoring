package com.example.springbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistsException extends ApiException {

    public AlreadyExistsException() {
        super(HttpStatus.CONFLICT, "This entity already exists");
    }

    public AlreadyExistsException(String message) {
        this();
        super.message = message;
    }

}
