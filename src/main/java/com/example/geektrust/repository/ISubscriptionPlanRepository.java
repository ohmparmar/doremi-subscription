package com.example.geektrust.repository;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;

public interface ISubscriptionPlanRepository {
    SubscriptionPlan getSubscriptionPlanByCategoryAndPlan(Category category, Plan plan);
    void addSubscriptionPlan(Category category, Plan plan , int months , int cost);

}
