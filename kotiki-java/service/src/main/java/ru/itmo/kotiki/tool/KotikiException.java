package ru.itmo.kotiki.tool;

public class KotikiException extends RuntimeException {

    public KotikiException() {
    }

    public KotikiException(String message) {
        super(message);
    }

    public KotikiException(String message, Exception exception) {
        super(message, exception);
    }
}
