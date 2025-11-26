package com.example.geektrust.exceptions;

public class InvalidSubscriptionCategoryException extends RuntimeException{
    public InvalidSubscriptionCategoryException(String message){
        super(message);
    }
}
