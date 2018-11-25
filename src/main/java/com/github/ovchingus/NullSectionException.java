package com.github.ovchingus;

public class NullSectionException extends IllegalArgumentException {
    public NullSectionException(String message) {
        super(message);
    }

    public NullSectionException(String message, Throwable cause) {
        super(message);
        this.initCause(cause);
    }

    public NullSectionException(Throwable cause) {
        this.initCause(cause);
    }
}
