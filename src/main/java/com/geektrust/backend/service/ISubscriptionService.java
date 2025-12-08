package com.geektrust.backend.service;

import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;

public interface ISubscriptionService {
    void startSubscription(String date);

    void addSubscription(Category category, Plan plan);
}
