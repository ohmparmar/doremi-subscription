package com.geektrust.backend.exceptions;

public class InvalidSubscriptionCategoryException extends RuntimeException {
    public InvalidSubscriptionCategoryException(String message) {
        super(message);
    }
}
