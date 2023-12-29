package com.sem4project.sem4.exception;

import java.io.Serial;

public class UpdateResourceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 9206987140880520886L;

    public UpdateResourceException() {
    }

    public UpdateResourceException(String message) {
        super(message);
    }

    public UpdateResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateResourceException(Throwable cause) {
        super(cause);
    }

    public UpdateResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
