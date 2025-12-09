package com.example.geektrust;

import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.commands.ICommand;
import com.example.geektrust.config.AppConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            AppConfig appConfig = new AppConfig();
            CommandInvoker commandInvoker = appConfig.getCommandInvoker();
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> tokens = parse(line);
                ICommand command = commandInvoker.get(tokens.get(0));
                command.execute(tokens);
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> parse(String command) {
        return Arrays.asList(command.split(" "));
    }
}
