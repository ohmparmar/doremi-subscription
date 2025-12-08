package com.geektrust.backend.exceptions;

public class AddSubscriptionFailed extends RuntimeException {
    public AddSubscriptionFailed(String message) {
        super(message);
    }
}
