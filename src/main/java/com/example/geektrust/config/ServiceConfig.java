package com.example.geektrust.config;


import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.service.IRenewalService;
import com.example.geektrust.service.ISubscriptionService;
import com.example.geektrust.service.ITopUpService;
import com.example.geektrust.service.implementation.RenewalService;
import com.example.geektrust.service.implementation.SubscriptionService;
import com.example.geektrust.service.implementation.TopUpService;

public class ServiceConfig {
    public static ISubscriptionService subscriptionService(
            IUserSubscriptionRepository userRepo,
            ISubscriptionPlanRepository planRepo) {
        return new SubscriptionService(userRepo, planRepo);
    }

    public static ITopUpService topUpService(
            IUserSubscriptionRepository userRepo,
            ITopUpRepository topUpRepo) {
        return new TopUpService(userRepo, topUpRepo);
    }

    public static IRenewalService renewalService(
            IUserSubscriptionRepository userRepo) {
        return new RenewalService(userRepo);
    }
}
