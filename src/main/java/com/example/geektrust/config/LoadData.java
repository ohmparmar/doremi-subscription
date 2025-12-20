package com.example.geektrust.config;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.ITopUpRepository;

public class LoadData {

    public static void loadPlans(ISubscriptionPlanRepository repo) {

        repo.addSubscriptionPlan(Category.MUSIC, Plan.FREE,
                Constants.MUSIC_FREE_PLAN_DURATION, Constants.MUSIC_FREE_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.MUSIC, Plan.PERSONAL,
                Constants.MUSIC_PERSONAL_PLAN_DURATION, Constants.MUSIC_PERSONAL_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.MUSIC, Plan.PREMIUM,
                Constants.MUSIC_PREMIUM_PLAN_DURATION, Constants.MUSIC_PREMIUM_PLAN_PRICE);

        repo.addSubscriptionPlan(Category.VIDEO, Plan.FREE,
                Constants.VIDEO_FREE_PLAN_DURATION, Constants.VIDEO_FREE_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.VIDEO, Plan.PERSONAL,
                Constants.VIDEO_PERSONAL_PLAN_DURATION, Constants.VIDEO_PERSONAL_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.VIDEO, Plan.PREMIUM,
                Constants.VIDEO_PREMIUM_PLAN_DURATION, Constants.VIDEO_PREMIUM_PLAN_PRICE);

        repo.addSubscriptionPlan(Category.PODCAST, Plan.FREE,
                Constants.PODCAST_FREE_PLAN_DURATION, Constants.PODCAST_FREE_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.PODCAST, Plan.PERSONAL,
                Constants.PODCAST_PERSONAL_PLAN_DURATION, Constants.PODCAST_PERSONAL_PLAN_PRICE);
        repo.addSubscriptionPlan(Category.PODCAST, Plan.PREMIUM,
                Constants.PODCAST_PREMIUM_PLAN_DURATION, Constants.PODCAST_PREMIUM_PLAN_PRICE);
    }

    public static void loadTopUps(ITopUpRepository repo) {
        repo.addTopUp(TopUpType.FOUR_DEVICE,
                Constants.FOUR_DEVICE_TOPUP_COST,
                Constants.FOUR_DEVICE_TOPUP_VALIDITY_IN_MONTHS);
        repo.addTopUp(TopUpType.TEN_DEVICE,
                Constants.TEN_DEVICE_TOPUP_COST,
                Constants.TEN_DEVICE_TOPUP_VALIDITY_IN_MONTHS);
    }
}