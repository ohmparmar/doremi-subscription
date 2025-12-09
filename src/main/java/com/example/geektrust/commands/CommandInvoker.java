package com.example.geektrust.commands;


import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, ICommand> commands = new HashMap<>();

    public ICommand get(String name) {
        return commands.get(name);
    }

    public void register(ICommand command, String name) {
        commands.putIfAbsent(name, command);
    }

}
