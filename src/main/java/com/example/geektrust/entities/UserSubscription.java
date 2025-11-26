package com.example.geektrust.entities;

import com.example.geektrust.entities.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserSubscription {
    private TopUp topup;
    private Map<Category, SubscriptionPlan> subscriptions;
    private LocalDate startDate;
    public UserSubscription() {
        this.subscriptions = new HashMap<>();
    }


    public boolean hasSubscription(Category category){
        if(subscriptions.containsKey(category)){
            return true;
        }else{
            return false;
        }
    }
    public boolean hasTopup(){
        if(topup!=null){
            return true;
        }else{
            return false;
        }
    }
    public boolean hasSubscription(){
        if(subscriptions.isEmpty()) return false;
        else return true;
    }
//    public void printSubscriptions() {
//        if (subscriptions == null || subscriptions.isEmpty()) {
//            System.out.println("No subscriptions added.");
//            return;
//        }
//        System.out.println("size- "+subscriptions.size());
//        subscriptions.forEach((category, plan) -> {
//            System.out.println(category + " → " + plan);
//        });
//    }


}
