package com.geektrust.backend.entities;

import com.geektrust.backend.entities.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class UserSubscription {
    private TopUp topup;
    private Map<Category, SubscriptionPlan> subscriptions;
    private LocalDate startDate;

    public UserSubscription() {
        this.subscriptions = new LinkedHashMap<>();
    }


    public boolean hasSubscription(Category category) {
        return subscriptions.containsKey(category);
    }

    public boolean hasTopup() {
        return topup != null;
    }

    public boolean hasSubscription() {
        return subscriptions.isEmpty();
    }

}
