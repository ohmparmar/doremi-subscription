package com.example.geektrust.entities;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.exceptions.AddSubscriptionFailed;
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

    public void addSubscription(SubscriptionPlan plan) {
        if (subscriptions.containsKey(plan.getCategory())) throw new AddSubscriptionFailed("DUPLICATE_CATEGORY");
        subscriptions.put(plan.getCategory(), plan);
    }

    public boolean hasSubscription(Category category) {
        return subscriptions.containsKey(category);
    }

    public boolean hasTopup() {
        return topup != null;
    }

    public boolean hasSubscription() {
        return !subscriptions.isEmpty();
    }

}
