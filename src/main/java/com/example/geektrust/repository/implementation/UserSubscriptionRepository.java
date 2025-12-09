package com.example.geektrust.repository.implementation;

import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.repository.IUserSubscriptionRepository;

public class UserSubscriptionRepository implements IUserSubscriptionRepository {
    private UserSubscription userSubscription;

    public UserSubscriptionRepository() {
        this.userSubscription = new UserSubscription();
    }


    @Override
    public UserSubscription getUserSubscription() {
        if (this.userSubscription == null) {
            this.userSubscription = new UserSubscription();
        }
        return this.userSubscription;
    }
}
