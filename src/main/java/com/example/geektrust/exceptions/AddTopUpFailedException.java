package com.example.geektrust.exceptions;

public class AddTopUpFailedException extends RuntimeException {

    public AddTopUpFailedException(String message) {
        super("ADD_TOPUP_FAILED "+message);
    }
}
