package com.haole.logistics.r2dbc.common;

public class JSONSerializeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JSONSerializeException(String message) {
        super(message);
    }

    public JSONSerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
