package com.example.springbackend.facade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response<T> {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private ResponseType type;
    private T data;
    private List<ErrorBody> errors;

    public Response(T data) {
        this.isSuccess = true;
        this.data = data;
        this.type = data == null ? ResponseType.NONE : ResponseType.OBJECT;
    }

    public Response(ErrorBody error) {
        this.isSuccess = false;
        this.errors = Collections.singletonList(error);
    }

    public Response(List<ErrorBody> errors) {
        this.isSuccess = false;
        this.errors = errors;
    }

    public Response(ErrorBody error, T data) {
        this.isSuccess = false;
        this.errors = Collections.singletonList(error);
        this.data = data;
        this.type = data == null ? ResponseType.NONE : ResponseType.OBJECT;
    }

    public static Response error(final ErrorBody errorBody) {
        return new Response(errorBody);
    }

    public static Response error(final List<ErrorBody> errorBodies) {
        return new Response(errorBodies);
    }

    public static Response error(final ErrorBody errorBody, final Object data) {
        return new Response(errorBody, data);
    }


    public static <T> ResponseEntity<Response<T>> failure(final T data) {
        Response<T> response = new Response<>(data);
        response.setSuccess(false);
        return ResponseEntity.ok(response);
    }


    public static <T> ResponseEntity<Response<T>> success(final T data) {
        Response<T> response = new Response<>(data);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Response<T>> success() {
        return ResponseEntity.ok(new Response<T>((T) null));
    }

    public enum ResponseType {
        OBJECT,
        ARRAY,
        VALUE,
        NONE
    }

}
