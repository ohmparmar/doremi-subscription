package com.geektrust.backend.repository;

import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.enums.TopUpType;

public interface ITopUpRepository {
    void addTopUp(TopUpType type, int cost, int months);

    TopUp getTopUp(TopUpType type);

}
