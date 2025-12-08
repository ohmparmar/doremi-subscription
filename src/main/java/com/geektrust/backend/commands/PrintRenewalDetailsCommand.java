package com.geektrust.backend.commands;

import com.geektrust.backend.service.IRenewalService;

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
