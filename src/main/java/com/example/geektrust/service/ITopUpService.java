package com.example.geektrust.service;

import com.example.geektrust.entities.enums.TopUpType;

public interface ITopUpService {
    void addTopUp(TopUpType topupType, int months);
}
