package com.geektrust.backend.service;

import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.entities.enums.TopUpType;
import com.geektrust.backend.exceptions.AddTopUpFailedException;
import com.geektrust.backend.repository.ITopUpRepository;
import com.geektrust.backend.repository.IUserSubscriptionRepository;
import com.geektrust.backend.service.implementation.TopUpService;

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
