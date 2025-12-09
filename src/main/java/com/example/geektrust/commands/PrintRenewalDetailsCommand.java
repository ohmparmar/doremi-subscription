package com.example.geektrust.commands;

import com.example.geektrust.service.IRenewalService;

import java.util.List;

public class PrintRenewalDetailsCommand implements ICommand {
    private final IRenewalService renewalService;

    public PrintRenewalDetailsCommand(IRenewalService renewalService) {
        this.renewalService = renewalService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            renewalService.printRenewalDetails();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
