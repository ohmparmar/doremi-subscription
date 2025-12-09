package com.example.geektrust.repository.implementation;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.repository.ISubscriptionPlanRepository;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionPlanRepository implements ISubscriptionPlanRepository {
    Map<Category, Map<Plan, SubscriptionPlan>> storage = new HashMap<>();

    @Override
    public SubscriptionPlan getSubscriptionPlanByCategoryAndPlan(Category category, Plan plan) {
        return storage.get(category).get(plan);
    }

    @Override
    public void addSubscriptionPlan(Category category, Plan plan, int months, int cost) {
        storage.putIfAbsent(category, new HashMap<>());
        Map<Plan, SubscriptionPlan> planMap = storage.get(category);
        if (planMap.containsKey(plan)) {
            throw new RuntimeException("This plan already exists");
        }

        SubscriptionPlan subscriptionPlan = new SubscriptionPlan(category, plan, months, cost);
        planMap.put(plan, subscriptionPlan);
    }
}
