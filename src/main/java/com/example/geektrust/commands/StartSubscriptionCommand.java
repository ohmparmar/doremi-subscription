package com.example.geektrust.commands;

import com.example.geektrust.exceptions.InvalidDateException;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.service.ISubscriptionService;
import com.example.geektrust.service.implementation.SubscriptionService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StartSubscriptionCommand implements ICommand {
    private ISubscriptionService subscriptionService;
    public StartSubscriptionCommand(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> tokens){
        try{
            String date = tokens.get(1);
            subscriptionService.startSubscription(date);
        }catch(InvalidDateException e){
            System.out.println("INVALID_DATE");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
