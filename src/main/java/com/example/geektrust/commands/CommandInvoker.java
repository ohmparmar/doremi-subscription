package com.example.geektrust.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private Map<String,ICommand> commands = new HashMap<>();
    public ICommand get (String name){
        return commands.get(name);
    }

    public void register(ICommand command , String name){
        commands.putIfAbsent(name,command);
    }
    public List<String> parse(String input){
        return Arrays.asList(input.split(" "));
    }
    public void execute(String input){
        List<String> tokens = parse(input);
        ICommand command = get(tokens.get(0));
        if (command==null){
            throw new RuntimeException("Command not found");
        }
        command.execute(tokens);
    }
}
