package com.example.geektrust;

import com.example.geektrust.app.ApplicationRunner;
import com.example.geektrust.config.AppConfig;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileInputStream(args[0]))){
            ApplicationRunner runner = AppConfig.createApplication();
            runner.run(sc);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
