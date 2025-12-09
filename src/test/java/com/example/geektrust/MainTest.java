package com.example.geektrust;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest {

    @Test
    void parseCommandBySpaceTest() {
        String command = "ADD_SUBSCRIPTION MUSIC PERSONAL";
        List<String> tokens = Main.parse(command);

        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals("ADD_SUBSCRIPTION", tokens.get(0));
        Assertions.assertEquals("MUSIC", tokens.get(1));
        Assertions.assertEquals("PERSONAL", tokens.get(2));
    }

    @Test
    void parseSingleTokenTest() {
        String command = "PRINT_RENEWAL_DETAILS";
        List<String> tokens = Main.parse(command);

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals("PRINT_RENEWAL_DETAILS", tokens.get(0));
    }
}
