package com.haole.logistics.r2dbc.common;

public class JSONParseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JSONParseException(String message) {
        super(message);
    }

    public JSONParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
