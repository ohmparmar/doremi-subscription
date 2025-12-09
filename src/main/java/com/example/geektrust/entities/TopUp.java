package com.example.geektrust.entities;


import com.example.geektrust.entities.enums.TopUpType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TopUp {
    private TopUpType type;
    private int cost;
    private int months;
}
