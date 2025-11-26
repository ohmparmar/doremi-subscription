package com.example.geektrust.service;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;

public interface ISubscriptionService {
    public void startSubscription(String date);
    public void addSubscription(Category category , Plan plan);
}
