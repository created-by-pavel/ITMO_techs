package ru.itmo.kotiki.catservice.service.tool;

public class CatServiceException extends RuntimeException {

    public CatServiceException() {
    }

    public CatServiceException(String message) {
        super(message);
    }

    public CatServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
