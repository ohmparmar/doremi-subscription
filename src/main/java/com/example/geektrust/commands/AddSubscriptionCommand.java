package com.example.geektrust.commands;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.exceptions.AddSubscriptionFailed;
import com.example.geektrust.exceptions.InvalidSubscriptionCategoryException;
import com.example.geektrust.service.ISubscriptionService;

import java.util.List;

public class AddSubscriptionCommand implements ICommand {

    private final ISubscriptionService subscriptionService;

    public AddSubscriptionCommand(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Category category = Category.valueOf(tokens.get(1));
            Plan plan = Plan.valueOf(tokens.get(2));
            subscriptionService.addSubscription(category, plan);
        } catch (IllegalArgumentException ex) {
            throw new InvalidSubscriptionCategoryException("INVALID_CATEGORY: " + tokens.get(1));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
