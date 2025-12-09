package com.example.geektrust;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.TopUp;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.exceptions.AddTopUpFailedException;
import com.example.geektrust.globals.Constants;
import com.example.geektrust.repository.ITopUpRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.repository.implementation.UserSubscriptionRepository;
import com.example.geektrust.service.implementation.TopUpService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;

import java.time.LocalDate;

public class TopUpServiceTest {

    private IUserSubscriptionRepository userSubscriptionRepository;
    private ITopUpRepository topUpRepository;
    private TopUpService topUpService;

    @BeforeEach
    void setup() {
        userSubscriptionRepository = new UserSubscriptionRepository();
        topUpRepository = Mockito.mock(ITopUpRepository.class);
        topUpService = new TopUpService(userSubscriptionRepository, topUpRepository);
    }

    @Test
    void AddTopUpWhenNoSubscriptionExists() {
        userSubscriptionRepository.getUserSubscription().setStartDate(LocalDate.now());
        AddTopUpFailedException ex = Assertions.assertThrows(
                AddTopUpFailedException.class,
                () -> topUpService.addTopUp(TopUpType.FOUR_DEVICE, 2)
        );

        Assertions.assertEquals("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND", ex.getMessage());
    }

    @Test
    void AddTopUpWhenTopUpAlreadyExists() {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        userSubscription.setStartDate(LocalDate.now());
        userSubscription.getSubscriptions().put(null, null); // simulate existing subscription
        userSubscription.setTopup(new TopUp(TopUpType.FOUR_DEVICE, 50, 1));

        AddTopUpFailedException ex = Assertions.assertThrows(
                AddTopUpFailedException.class,
                () -> topUpService.addTopUp(TopUpType.FOUR_DEVICE, 1)
        );

        Assertions.assertEquals("ADD_TOPUP_FAILED DUPLICATE_TOPUP", ex.getMessage());
    }

    @Test
    void AddTopUpSuccess() {
        UserSubscription userSubscription = userSubscriptionRepository.getUserSubscription();
        userSubscription.setStartDate(LocalDate.now());
        userSubscription.getSubscriptions().put(Category.MUSIC, new SubscriptionPlan(Category.MUSIC, Plan.FREE, Constants.MUSIC_FREE_PLAN_DURATION, Constants.MUSIC_FREE_PLAN_PRICE));

        Mockito.when(topUpRepository.getTopUp(TopUpType.FOUR_DEVICE))
                .thenReturn(new TopUp(TopUpType.FOUR_DEVICE, 50, 1));

        topUpService.addTopUp(TopUpType.FOUR_DEVICE, 3);

        Assertions.assertNotNull(userSubscription.getTopup());
        Assertions.assertEquals(150, userSubscription.getTopup().getCost());
        Assertions.assertEquals(3, userSubscription.getTopup().getMonths());
    }
}
