package com.example.geektrust.commands;

import com.example.geektrust.entities.enums.TopUpType;
import com.example.geektrust.exceptions.InvalidDateException;

import com.example.geektrust.service.ITopUpService;

import java.util.List;

public class AddTopupCommand implements ICommand {
    private final ITopUpService topUpService;

    public AddTopupCommand(ITopUpService topUpService) {
        this.topUpService = topUpService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            TopUpType topupType = TopUpType.valueOf(tokens.get(1));
            int months = Integer.parseInt(tokens.get(2));
            topUpService.addTopUp(topupType, months);
        } catch (InvalidDateException e) {
            System.out.println("INVALID_TOPUP_TYPE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
