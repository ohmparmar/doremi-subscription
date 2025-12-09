package com.example.geektrust.repository;

import com.example.geektrust.entities.TopUp;
import com.example.geektrust.entities.enums.TopUpType;

public interface ITopUpRepository {
    void addTopUp(TopUpType type, int cost, int months);

    TopUp getTopUp(TopUpType type);

}
