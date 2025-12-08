package com.geektrust.backend.exceptions;

public class AddTopUpFailedException extends RuntimeException {

    public AddTopUpFailedException(String message) {
        super(message);
    }
}
