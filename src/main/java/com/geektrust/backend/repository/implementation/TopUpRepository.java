package com.geektrust.backend.repository.implementation;

import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.enums.TopUpType;
import com.geektrust.backend.repository.ITopUpRepository;

import java.util.HashMap;
import java.util.Map;

public class TopUpRepository implements ITopUpRepository {
    private Map<TopUpType, TopUp> storage = new HashMap<>();

    @Override
    public void addTopUp(TopUpType type, int cost, int months) {
        storage.put(type, new TopUp(type, cost, months));
    }

    @Override
    public TopUp getTopUp(TopUpType type) {
        return storage.get(type);

    }
}
