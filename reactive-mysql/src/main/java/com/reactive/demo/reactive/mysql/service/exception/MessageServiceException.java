package com.reactive.demo.reactive.mysql.service.exception;

public class MessageServiceException extends RuntimeException {


    public MessageServiceException(String message) {
        super(message);
    }

    public MessageServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MessageServiceException(Throwable throwable) {
        super(throwable);
    }

    public MessageServiceException() {
        super();
    }
}
