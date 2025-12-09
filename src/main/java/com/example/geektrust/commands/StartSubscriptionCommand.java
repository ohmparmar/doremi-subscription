package com.example.geektrust.commands;

import com.example.geektrust.exceptions.InvalidDateException;
import com.example.geektrust.service.ISubscriptionService;

import java.util.List;

public class StartSubscriptionCommand implements ICommand {
    private final ISubscriptionService subscriptionService;

    public StartSubscriptionCommand(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String date = tokens.get(1);
            subscriptionService.startSubscription(date);
        } catch (InvalidDateException e) {
            System.out.println("INVALID_DATE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
