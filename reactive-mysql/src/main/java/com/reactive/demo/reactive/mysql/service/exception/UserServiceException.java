package com.reactive.demo.reactive.mysql.service.exception;

public class UserServiceException extends RuntimeException {


    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserServiceException(Throwable throwable) {
        super(throwable);
    }

    public UserServiceException() {
        super();
    }

}
