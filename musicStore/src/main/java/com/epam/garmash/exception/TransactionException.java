package com.epam.garmash.exception;

public class TransactionException extends RuntimeException {

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

}
