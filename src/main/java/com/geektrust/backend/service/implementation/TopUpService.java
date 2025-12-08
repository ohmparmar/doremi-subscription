package com.geektrust.backend.service.implementation;

import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.entities.enums.TopUpType;
import com.geektrust.backend.exceptions.AddTopUpFailedException;
import com.geektrust.backend.repository.ITopUpRepository;
import com.geektrust.backend.repository.IUserSubscriptionRepository;
import com.geektrust.backend.service.ITopUpService;


public class TopUpService implements ITopUpService {

    private final IUserSubscriptionRepository userSubscriptionRepository;
    private final ITopUpRepository topUpRepository;

    public TopUpService(IUserSubscriptionRepository userSubscriptionRepository, ITopUpRepository topUpRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.topUpRepository = topUpRepository;
    }

    @Override
    public void addTopUp(TopUpType topupType, int months) {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        if (userSubscription == null || userSubscription.hasSubscription()) {
            throw new AddTopUpFailedException("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND");
        }
        if (userSubscription.hasTopup()) {
            throw new AddTopUpFailedException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        TopUp BaseTopUp = topUpRepository.getTopUp(topupType);
        userSubscription.setTopup(new TopUp(topupType, BaseTopUp.getCost() * months, months));
    }
}
