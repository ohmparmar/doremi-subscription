package com.example.geektrust.app;

import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.commands.ICommand;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ApplicationRunner {
    private final CommandInvoker commandInvoker;

    public ApplicationRunner(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void run(Scanner sc) {
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            List<String> token = parse(input);
            try{
                ICommand command = commandInvoker.get(token.get(0));
                command.execute(token);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private List<String> parse(String command) {
        return Arrays.asList(command.split(" "));
    }
}
