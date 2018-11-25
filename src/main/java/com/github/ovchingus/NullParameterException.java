package com.github.ovchingus;

public class NullParameterException extends IllegalArgumentException {
    public NullParameterException(String message) {
        super(message);
    }

    public NullParameterException(String message, Throwable cause) {
        super(message);
        this.initCause(cause);
    }

    public NullParameterException(Throwable cause) {
        this.initCause(cause);
    }
}
