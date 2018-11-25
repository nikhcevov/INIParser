package com.github.ovchingus;

import java.io.IOException;

public class InvalidIniFormatException extends IOException {
    public InvalidIniFormatException(String message) {
        super(message);
    }

    public InvalidIniFormatException(String message, Throwable cause) {
        super(message);
        this.initCause(cause);
    }

    public InvalidIniFormatException(Throwable cause) {
        this.initCause(cause);
    }
}
