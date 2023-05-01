package org.example;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        Scheduler scheduler = new Scheduler(dataLoader);
        scheduler.printSchedule(args[0], args[1]);

    }
}
