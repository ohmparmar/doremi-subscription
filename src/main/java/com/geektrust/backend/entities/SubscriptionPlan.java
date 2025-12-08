package com.geektrust.backend.entities;

import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class SubscriptionPlan {

    private Category category;
    private Plan plan;
    private int months;
    private int cost;

}
