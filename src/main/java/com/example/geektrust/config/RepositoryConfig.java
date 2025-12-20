package com.example.geektrust.config;

import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.repository.implementation.SubscriptionPlanRepository;
import com.example.geektrust.repository.implementation.TopUpRepository;
import com.example.geektrust.repository.implementation.UserSubscriptionRepository;

public class RepositoryConfig {
    public static IUserSubscriptionRepository userSubscriptionRepository() {
        return new UserSubscriptionRepository();
    }

    public static ISubscriptionPlanRepository subscriptionPlanRepository() {
        return new SubscriptionPlanRepository();
    }

    public static ITopUpRepository topUpRepository() {
        return new TopUpRepository();
    }
}
