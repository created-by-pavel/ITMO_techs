package ru.itmo.kotiki.mainservice.service.tool;

public class MainServiceException extends RuntimeException {

    MainServiceException() {
    }

    public MainServiceException(String message) {
        super(message);
    }

    public MainServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
