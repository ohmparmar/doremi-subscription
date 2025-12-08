package com.geektrust.backend.service;

import com.geektrust.backend.entities.SubscriptionPlan;
import com.geektrust.backend.entities.UserSubscription;
import com.geektrust.backend.entities.enums.Category;
import com.geektrust.backend.exceptions.SubscriptionNotFoundException;
import com.geektrust.backend.repository.IUserSubscriptionRepository;
import com.geektrust.backend.service.implementation.RenewalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.mockito.Mockito;

public class RenewalServiceTest {

    private IUserSubscriptionRepository userRepository;
    private RenewalService renewalService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(IUserSubscriptionRepository.class);
        renewalService = new RenewalService(userRepository);
    }

    @Test
    void NoSubscriptionsExistTest() {
        UserSubscription emptyUser = new UserSubscription();
        Mockito.when(userRepository.getUserSubscription()).thenReturn(emptyUser);

        Assertions.assertThrows(
                SubscriptionNotFoundException.class,
                () -> renewalService.printRenewalDetails()
        );
    }

    @Test
    void PrintCorrectRenewalDetailsTest() {
        UserSubscription user = new UserSubscription();
        user.setStartDate(LocalDate.of(2022, 2, 20));

        SubscriptionPlan music = new SubscriptionPlan(Category.MUSIC, null, 1, 100);
        SubscriptionPlan video = new SubscriptionPlan(Category.VIDEO, null, 3, 500);

        user.getSubscriptions().put(Category.MUSIC, music);
        user.getSubscriptions().put(Category.VIDEO, video);

        Mockito.when(userRepository.getUserSubscription()).thenReturn(user);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        renewalService.printRenewalDetails();

        String output = out.toString().trim();

        Assertions.assertTrue(output.contains("RENEWAL_REMINDER MUSIC 10-03-2022"));
        Assertions.assertTrue(output.contains("RENEWAL_REMINDER VIDEO 10-05-2022"));
        Assertions.assertTrue(output.contains("RENEWAL_AMOUNT 600"));
    }
}
