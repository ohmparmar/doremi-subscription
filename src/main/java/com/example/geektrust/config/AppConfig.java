package com.example.geektrust.config;

import com.example.geektrust.commands.*;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.repository.implementation.SubscriptionPlanRepository;
import com.example.geektrust.repository.implementation.TopUpRepository;
import com.example.geektrust.repository.implementation.UserSubscriptionRepository;
import com.example.geektrust.service.IRenewalService;
import com.example.geektrust.service.ISubscriptionService;
import com.example.geektrust.service.ITopUpService;
import com.example.geektrust.service.implementation.RenewalService;
import com.example.geektrust.service.implementation.SubscriptionService;
import com.example.geektrust.service.implementation.TopUpService;
import lombok.Getter;

@Getter
public class AppConfig {
    private final CommandInvoker commandInvoker;
    private final ISubscriptionService subscriptionService;
    private final ITopUpService topUpService;
    private final IRenewalService renewalService;
    private final IUserSubscriptionRepository userSubscriptionRepository;
    private final ISubscriptionPlanRepository subscriptionPlanRepository;
    private final ITopUpRepository topUpRepository;

    public AppConfig() {
        this.commandInvoker = new CommandInvoker();
        this.userSubscriptionRepository = new UserSubscriptionRepository();
        this.subscriptionPlanRepository = new SubscriptionPlanRepository();
        this.topUpRepository = new TopUpRepository();
        this.subscriptionService = new SubscriptionService(userSubscriptionRepository, subscriptionPlanRepository);
        this.topUpService = new TopUpService(userSubscriptionRepository, topUpRepository);
        this.renewalService = new RenewalService(userSubscriptionRepository);
        registerCommands();
        addSubscriptionPlan();
        addTopUp();
    }

    public void registerCommands() {
        commandInvoker.register(new StartSubscriptionCommand(subscriptionService), "START_SUBSCRIPTION");
        commandInvoker.register(new AddSubscriptionCommand(subscriptionService), "ADD_SUBSCRIPTION");
        commandInvoker.register(new AddTopupCommand(topUpService), "ADD_TOPUP");
        commandInvoker.register(new PrintRenewalDetailsCommand(renewalService), "PRINT_RENEWAL_DETAILS");

    }

    public void addSubscriptionPlan() {
        subscriptionPlanRepository.addSubscriptionPlan(Category.MUSIC, Plan.FREE, Constants.MUSIC_FREE_PLAN_DURATION, Constants.MUSIC_FREE_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.MUSIC, Plan.PERSONAL, Constants.MUSIC_PERSONAL_PLAN_DURATION, Constants.MUSIC_PERSONAL_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.MUSIC, Plan.PREMIUM, Constants.MUSIC_PREMIUM_PLAN_DURATION, Constants.MUSIC_PREMIUM_PLAN_PRICE);

        subscriptionPlanRepository.addSubscriptionPlan(Category.VIDEO, Plan.FREE, Constants.VIDEO_FREE_PLAN_DURATION, Constants.VIDEO_FREE_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.VIDEO, Plan.PERSONAL, Constants.VIDEO_PERSONAL_PLAN_DURATION, Constants.VIDEO_PERSONAL_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.VIDEO, Plan.PREMIUM, Constants.VIDEO_PREMIUM_PLAN_DURATION, Constants.VIDEO_PREMIUM_PLAN_PRICE);


        subscriptionPlanRepository.addSubscriptionPlan(Category.PODCAST, Plan.FREE, Constants.PODCAST_FREE_PLAN_DURATION, Constants.PODCAST_FREE_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.PODCAST, Plan.PERSONAL, Constants.PODCAST_PERSONAL_PLAN_DURATION, Constants.PODCAST_PERSONAL_PLAN_PRICE);
        subscriptionPlanRepository.addSubscriptionPlan(Category.PODCAST, Plan.PREMIUM, Constants.PODCAST_PREMIUM_PLAN_DURATION, Constants.PODCAST_PREMIUM_PLAN_PRICE);

    }

    public void addTopUp() {
        topUpRepository.addTopUp(TopUpType.FOUR_DEVICE, Constants.FOUR_DEVICE_TOPUP_COST, Constants.FOUR_DEVICE_TOPUP_VALIDITY_IN_MONTHS);
        topUpRepository.addTopUp(TopUpType.TEN_DEVICE, Constants.TEN_DEVICE_TOPUP_COST, Constants.TEN_DEVICE_TOPUP_VALIDITY_IN_MONTHS);
    }
}
