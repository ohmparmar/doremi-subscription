package com.geektrust.backend.repository.implementation;

import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.repository.IUserSubscriptionRepository;

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
