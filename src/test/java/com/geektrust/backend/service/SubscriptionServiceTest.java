package com.geektrust.backend.service;

import com.geektrust.backend.entities.SubscriptionPlan;
import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.entities.enums.Plan;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import com.geektrust.backend.exceptions.InvalidDateException;
import com.geektrust.backend.repository.ISubscriptionPlanRepository;
import com.geektrust.backend.repository.IUserSubscriptionRepository;
import com.geektrust.backend.service.implementation.SubscriptionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

import org.mockito.Mockito;

public class SubscriptionServiceTest {

    private IUserSubscriptionRepository userRepository;
    private ISubscriptionPlanRepository planRepository;
    private SubscriptionService subscriptionService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(IUserSubscriptionRepository.class);
        planRepository = Mockito.mock(ISubscriptionPlanRepository.class);
        subscriptionService = new SubscriptionService(userRepository, planRepository);
    }

    @Test
    void DateFormatTest() {
        UserSubscription user = new UserSubscription();
        Mockito.when(userRepository.getUserSubscription()).thenReturn(user);

        Assertions.assertThrows(
                InvalidDateException.class,
                () -> subscriptionService.startSubscription("2022-10-05")
        );
    }

    @Test
    void SetStartDateSuccess() {
        UserSubscription user = new UserSubscription();
        Mockito.when(userRepository.getUserSubscription()).thenReturn(user);

        subscriptionService.startSubscription("05-10-2022");

        Assertions.assertEquals(LocalDate.of(2022, 10, 5), user.getStartDate());
    }

    @Test
    void DuplicateCategoryAdditionTest() {
        UserSubscription user = new UserSubscription();
        user.getSubscriptions().put(
                Category.MUSIC,
                new SubscriptionPlan(Category.MUSIC, Plan.FREE, 1, 0)
        );

        Mockito.when(userRepository.getUserSubscription()).thenReturn(user);

        Assertions.assertThrows(
                AddSubscriptionFailed.class,
                () -> subscriptionService.addSubscription(Category.MUSIC, Plan.PERSONAL)
        );
    }

    @Test
    void AddSubscriptionSuccess() {
        UserSubscription user = new UserSubscription();
        Mockito.when(userRepository.getUserSubscription()).thenReturn(user);

        SubscriptionPlan plan = new SubscriptionPlan(Category.VIDEO, Plan.PERSONAL, 1, 200);
        Mockito.when(planRepository.getSubscriptionPlanByCategoryAndPlan(Category.VIDEO, Plan.PERSONAL))
                .thenReturn(plan);

        subscriptionService.addSubscription(Category.VIDEO, Plan.PERSONAL);

        Assertions.assertTrue(user.hasSubscription(Category.VIDEO));
        Assertions.assertEquals(plan, user.getSubscriptions().get(Category.VIDEO));
    }
}
