package com.example.geektrust.service.implementation;

import com.example.geektrust.entities.TopUp;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.exceptions.AddTopUpFailedException;
import com.example.geektrust.exceptions.SubscriptionNotFoundException;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.service.ITopUpService;


public class TopUpService implements ITopUpService {

    private IUserSubscriptionRepository userSubscriptionRepository;
    private ITopUpRepository topUpRepository;

    public TopUpService(IUserSubscriptionRepository userSubscriptionRepository,ITopUpRepository topUpRepository){
        this.userSubscriptionRepository=userSubscriptionRepository;
        this.topUpRepository =  topUpRepository;
    }
    
    @Override
    public void addTopUp(TopUpType topupType, int months){
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        if(userSubscription==null){
            throw new SubscriptionNotFoundException("SUBSCRIPTIONS_NOT_FOUND");
        }
        if(userSubscription.hasTopup()){
            throw new AddTopUpFailedException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        TopUp BaseTopUp = topUpRepository.getTopUp(topupType);
        userSubscription.setTopup(new TopUp(topupType,BaseTopUp.getCost()*months,months));
    }
}
