package org.example.exception;

public class DuplicateIDException extends RuntimeException {
    public DuplicateIDException(String message) {
        super(message);
    }
}
