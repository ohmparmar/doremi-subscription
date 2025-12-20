package com.example.geektrust.config;

import com.example.geektrust.commands.*;
import com.example.geektrust.service.IRenewalService;
import com.example.geektrust.service.ISubscriptionService;
import com.example.geektrust.service.ITopUpService;

public class CommandConfig {
    public static CommandInvoker commandInvoker(
            ISubscriptionService subscriptionService,
            ITopUpService topUpService,
             IRenewalService renewalService) {

        CommandInvoker invoker = new CommandInvoker();
        invoker.register(new StartSubscriptionCommand(subscriptionService), "START_SUBSCRIPTION");
        invoker.register(new AddSubscriptionCommand(subscriptionService), "ADD_SUBSCRIPTION");
        invoker.register(new AddTopupCommand(topUpService), "ADD_TOPUP");
        invoker.register(new PrintRenewalDetailsCommand(renewalService), "PRINT_RENEWAL_DETAILS");

        return invoker;
    }

}
