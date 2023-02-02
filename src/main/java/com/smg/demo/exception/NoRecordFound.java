package com.smg.demo.exception;

import java.io.Serial;

public class NoRecordFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NoRecordFound(String message) {
        super(message);
    }
}
