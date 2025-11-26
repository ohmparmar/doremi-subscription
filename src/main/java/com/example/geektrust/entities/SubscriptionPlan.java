package com.example.geektrust.entities;

import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
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
