package com.geektrust.backend.repository;

import com.geektrust.backend.entities.SubscriptionPlan;
import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;

public interface ISubscriptionPlanRepository {
    SubscriptionPlan getSubscriptionPlanByCategoryAndPlan(Category category, Plan plan);

    void addSubscriptionPlan(Category category, Plan plan, int months, int cost);

}
