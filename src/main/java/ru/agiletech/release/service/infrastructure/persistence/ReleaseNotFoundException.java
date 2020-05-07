package ru.agiletech.release.service.infrastructure.persistence;

public class ReleaseNotFoundException extends RuntimeException{

    public ReleaseNotFoundException() {
    }

    public ReleaseNotFoundException(String message) {
        super(message);
    }

    public ReleaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReleaseNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReleaseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
