package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Event> events=Simulation.builder()
                .days(30)
                .step(5)
                .carAmount(200)
                .addParking(20)
                .build()
                .run();
        System.out.println(events.size());
        events.forEach(System.out::println);
    }
}
