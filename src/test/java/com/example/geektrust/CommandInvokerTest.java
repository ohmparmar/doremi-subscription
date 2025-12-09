package com.example.geektrust;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.commands.ICommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class CommandInvokerTest {

    private static class DummyCommand implements ICommand {
        boolean executed = false;

        @Override
        public void execute(List<String> tokens) {
            executed = true;
        }
    }

    @Test
    void registerAndGetShouldReturnSameInstance() {
        CommandInvoker invoker = new CommandInvoker();
        DummyCommand command = new DummyCommand();

        invoker.register(command, "START");

        ICommand retrieved = invoker.get("START");

        Assertions.assertSame(command, retrieved);
    }

    @Test
    void getUnknownCommandShouldReturnNull() {
        CommandInvoker invoker = new CommandInvoker();

        ICommand retrieved = invoker.get("UNKNOWN");

        Assertions.assertNull(retrieved);
    }

    @Test
    void retrievedCommandShouldBeExecutable() {
        CommandInvoker invoker = new CommandInvoker();
        DummyCommand command = new DummyCommand();

        invoker.register(command, "DO_SOMETHING");
        ICommand retrieved = invoker.get("DO_SOMETHING");

        retrieved.execute(Collections.emptyList());

        Assertions.assertTrue(command.executed);
    }
}
