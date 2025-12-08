package com.geektrust.backend.service.implementation;

import com.geektrust.backend.entities.SubscriptionPlan;
import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import com.geektrust.backend.exceptions.InvalidDateException;
import com.geektrust.backend.globals.Constants;
import com.geektrust.backend.repository.ISubscriptionPlanRepository;
import com.geektrust.backend.repository.IUserSubscriptionRepository;
import com.geektrust.backend.service.ISubscriptionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SubscriptionService implements ISubscriptionService {
    private final DateTimeFormatter formatter;
    private final IUserSubscriptionRepository userSubscriptionRepository;
    private final ISubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionService(IUserSubscriptionRepository userSubscriptionRepository, ISubscriptionPlanRepository subscriptionPlanRepository) {
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    @Override
    public void startSubscription(String date) {
        try {
            LocalDate startDate = LocalDate.parse(date, formatter);
            UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
            userSubscription.setStartDate(startDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("INVALID_DATE");
        }
    }

    @Override
    public void addSubscription(Category category, Plan plan) {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        if (userSubscription.hasSubscription(category)) {
            throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getSubscriptionPlanByCategoryAndPlan(category, plan);
        userSubscription.getSubscriptions().put(category, subscriptionPlan);
    }
}
