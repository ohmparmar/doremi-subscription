package com.example.geektrust.service.implementation;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.exceptions.AddSubscriptionFailed;
import com.example.geektrust.exceptions.InvalidDateException;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.service.ISubscriptionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SubscriptionService implements ISubscriptionService {
    private final DateTimeFormatter formatter;
    private final IUserSubscriptionRepository userSubscriptionRepository;
    private final ISubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionService(IUserSubscriptionRepository userSubscriptionRepository,
            ISubscriptionPlanRepository subscriptionPlanRepository) {
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    @Override
    public void startSubscription(String date) {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        try {
            LocalDate startDate = LocalDate.parse(date, formatter);
            userSubscription.setStartDate(startDate);
        } catch (DateTimeParseException e) {
            userSubscription.setStartDate(null);
            throw new InvalidDateException("INVALID_DATE");
        }
    }


    @Override
    public void addSubscription(Category category, Plan plan) {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        if(isStartDateNull(userSubscription)) throw new AddSubscriptionFailed("INVALID_DATE");
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getSubscriptionPlanByCategoryAndPlan(category,
                plan);
        userSubscription.addSubscription(subscriptionPlan);
    }

    public static boolean isStartDateNull(UserSubscription userSubscription) {
        return userSubscription.getStartDate() == null;
    }

}
