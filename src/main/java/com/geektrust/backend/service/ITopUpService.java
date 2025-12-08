package com.geektrust.backend.service;

import com.geektrust.backend.entities.enums.TopUpType;

public interface ITopUpService {
    void addTopUp(TopUpType topupType, int months);
}
