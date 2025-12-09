package com.example.geektrust.exceptions;

public class AddSubscriptionFailed extends RuntimeException {
    public AddSubscriptionFailed(String message) {
        super("ADD_SUBSCRIPTION_FAILED " + message);
    }
}
