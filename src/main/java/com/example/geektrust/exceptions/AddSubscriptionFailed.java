package com.example.geektrust.exceptions;

public class AddSubscriptionFailed extends RuntimeException{
    public AddSubscriptionFailed(String message){
        super(message);
    }
}
