package com.geektrust.backend.entities;


import com.geektrust.backend.entities.enums.TopUpType;
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
