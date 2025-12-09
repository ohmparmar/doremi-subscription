package com.example.geektrust.service;

import com.example.geektrust.entities.TopUp;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.exceptions.AddTopUpFailedException;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.service.implementation.TopUpService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;

public class TopUpServiceTest {

    private IUserSubscriptionRepository userSubscriptionRepository;
    private ITopUpRepository topUpRepository;
    private TopUpService topUpService;

    @BeforeEach
    void setup() {
        userSubscriptionRepository = Mockito.mock(IUserSubscriptionRepository.class);
        topUpRepository = Mockito.mock(ITopUpRepository.class);
        topUpService = new TopUpService(userSubscriptionRepository, topUpRepository);
    }

    @Test
    void AddTopUpWhenNoSubscriptionExists() {
        UserSubscription emptyUser = new UserSubscription(); // no subscriptions
        Mockito.when(userSubscriptionRepository.getUserSubscription()).thenReturn(emptyUser);

        AddTopUpFailedException ex = Assertions.assertThrows(
                AddTopUpFailedException.class,
                () -> topUpService.addTopUp(TopUpType.FOUR_DEVICE, 2)
        );

        Assertions.assertEquals("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND", ex.getMessage());
    }

    @Test
    void AddTopUpWhenTopUpAlreadyExists() {
        UserSubscription user = new UserSubscription();
        user.getSubscriptions().put(null, null); // simulate existing subscription
        user.setTopup(new TopUp(TopUpType.FOUR_DEVICE, 50, 1));

        Mockito.when(userSubscriptionRepository.getUserSubscription()).thenReturn(user);

        AddTopUpFailedException ex = Assertions.assertThrows(
                AddTopUpFailedException.class,
                () -> topUpService.addTopUp(TopUpType.FOUR_DEVICE, 1)
        );

        Assertions.assertEquals("ADD_TOPUP_FAILED DUPLICATE_TOPUP", ex.getMessage());
    }

    @Test
    void AddTopUpSuccess() {
        UserSubscription user = new UserSubscription();
        user.getSubscriptions().put(null, null);

        Mockito.when(userSubscriptionRepository.getUserSubscription()).thenReturn(user);
        Mockito.when(topUpRepository.getTopUp(TopUpType.FOUR_DEVICE))
                .thenReturn(new TopUp(TopUpType.FOUR_DEVICE, 50, 1));

        topUpService.addTopUp(TopUpType.FOUR_DEVICE, 3);

        Assertions.assertNotNull(user.getTopup());
        Assertions.assertEquals(150, user.getTopup().getCost());
        Assertions.assertEquals(3, user.getTopup().getMonths());
    }
}
