package com.example.geektrust.service;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;

public interface ISubscriptionService {
    void startSubscription(String date);

    void addSubscription(Category category, Plan plan);
}
