package com.company.task.exception;

public class UnableToMapFileException extends RuntimeException {
    public UnableToMapFileException() {
    }

    public UnableToMapFileException(String message) {
        super(message);
    }

    public UnableToMapFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToMapFileException(Throwable cause) {
        super(cause);
    }

    public UnableToMapFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
