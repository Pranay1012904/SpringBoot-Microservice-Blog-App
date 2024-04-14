package com.blog.app.one.exception;

import org.springframework.http.HttpStatus;

public class CommentApiException extends RuntimeException {
    public CommentApiException(HttpStatus httpStatus, String message) {
        super(String.format(message, httpStatus));
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
