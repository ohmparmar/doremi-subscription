package com.geektrust.backend.commands;

import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import com.geektrust.backend.exceptions.InvalidSubscriptionCategoryException;
import com.geektrust.backend.service.ISubscriptionService;

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
        } catch (AddSubscriptionFailed ex) {
            throw new AddSubscriptionFailed(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
