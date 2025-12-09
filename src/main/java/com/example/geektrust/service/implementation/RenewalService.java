package com.example.geektrust.service.implementation;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.exceptions.SubscriptionNotFoundException;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.service.IRenewalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RenewalService implements IRenewalService {

    private final DateTimeFormatter formatter;
    private final IUserSubscriptionRepository userSubscriptionRepository;

    public RenewalService(IUserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
    }

    @Override
    public void printRenewalDetails() {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        if (userSubscription == null || userSubscription.hasSubscription()) {
            throw new SubscriptionNotFoundException("SUBSCRIPTIONS_NOT_FOUND");
        }
        Map<Category, SubscriptionPlan> subscriptions = userSubscription.getSubscriptions();
        int total = 0;
        List<Category> sortedCategories = new ArrayList<>(subscriptions.keySet());
        sortedCategories.sort(Comparator.comparing(Enum::name));

        for (Category category : sortedCategories) {
            LocalDate reminderDate = calculateReminderDate(userSubscription.getStartDate(),
                    subscriptions.get(category));
            System.out.println("RENEWAL_REMINDER " + category + " " + reminderDate.format(formatter));
            total += subscriptions.get(category).getCost();
        }

        if (userSubscription.getTopup() != null) {
            total += userSubscription.getTopup().getCost();
        }
        System.out.println("RENEWAL_AMOUNT " + total);

    }

    private LocalDate calculateReminderDate(LocalDate subscriptionStartDate, SubscriptionPlan subscriptionPlan) {
        return subscriptionStartDate.plusMonths(subscriptionPlan.getMonths()).minusDays(Constants.REMINDER_BEFORE_DAYS);
    }

}
