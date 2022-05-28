package ru.itmo.kotiki.ownerservice.service.tool;

public class OwnerServiceException extends RuntimeException {
    public OwnerServiceException() {
    }

    public OwnerServiceException(String message) {
        super(message);
    }

    public OwnerServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
