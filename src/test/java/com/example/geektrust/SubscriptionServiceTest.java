package com.example.geektrust;

import com.example.geektrust.entities.SubscriptionPlan;
import com.example.geektrust.entities.UserSubscription;
import com.example.geektrust.entities.enums.Category;
import com.example.geektrust.entities.enums.Plan;
import com.example.geektrust.exceptions.AddSubscriptionFailed;
import com.example.geektrust.exceptions.InvalidDateException;
import com.example.geektrust.repository.ISubscriptionPlanRepository;
import com.example.geektrust.repository.IUserSubscriptionRepository;
import com.example.geektrust.repository.implementation.UserSubscriptionRepository;
import com.example.geektrust.service.implementation.SubscriptionService;

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
        userRepository = new UserSubscriptionRepository(); // fresh instance
        planRepository = Mockito.mock(ISubscriptionPlanRepository.class);

        subscriptionService = new SubscriptionService(userRepository, planRepository);
    }

    @Test
    void DateFormatTest() {
        Assertions.assertThrows(
                InvalidDateException.class,
                () -> subscriptionService.startSubscription("2022-10-05")
        );
    }

    @Test
    void SetStartDateSuccess() {
        userRepository.getUserSubscription().setStartDate(LocalDate.now());
        Assertions.assertEquals(LocalDate.now(), userRepository.getUserSubscription().getStartDate());
    }

    @Test
    void DuplicateCategoryAdditionTest() {
        userRepository.getUserSubscription().setStartDate(LocalDate.now());
        userRepository.getUserSubscription().getSubscriptions().put(
                Category.MUSIC,
                new SubscriptionPlan(Category.MUSIC, Plan.FREE, 1, 0)
        );

        SubscriptionPlan musicPlan = new SubscriptionPlan(Category.MUSIC, Plan.PERSONAL, 1, 100);
        Mockito.when(planRepository.getSubscriptionPlanByCategoryAndPlan(Category.MUSIC, Plan.PERSONAL))
                .thenReturn(musicPlan);
        Assertions.assertThrows(
                AddSubscriptionFailed.class,
                () -> subscriptionService.addSubscription(Category.MUSIC, Plan.PERSONAL)
        );
    }

    @Test
    void AddSubscriptionSuccess() {

        userRepository.getUserSubscription().setStartDate(LocalDate.now());

        SubscriptionPlan plan = new SubscriptionPlan(Category.VIDEO, Plan.PERSONAL, 1, 200);
        Mockito.when(planRepository.getSubscriptionPlanByCategoryAndPlan(Category.VIDEO, Plan.PERSONAL))
                .thenReturn(plan);

        subscriptionService.addSubscription(Category.VIDEO, Plan.PERSONAL);

        Assertions.assertTrue(userRepository.getUserSubscription().hasSubscription(Category.VIDEO));
        Assertions.assertEquals(plan, userRepository.getUserSubscription().getSubscriptions().get(Category.VIDEO));
    }
}
