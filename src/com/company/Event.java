package com.company;

import java.time.LocalDateTime;

public class Event {
    public enum Stage {
        ENTER, EXIT
    }
    private final Stage stage;
    private final LocalDateTime ldt;
    private final String carNumber;

    public Event(Stage stage, LocalDateTime ldt, Car car) {
        this.stage = stage;
        this.ldt = ldt;
        this.carNumber = car.getNumbersOfCar();
    }

    public static Event registerEnter(LocalDateTime ldt, Car car){
        return new Event(Stage.ENTER,ldt,car);

    }
    public static Event registerExit(LocalDateTime ldt,Car car){
        return new Event(Stage.EXIT,ldt,car);
    }

    @Override
    public String toString() {
        return "Event:" +
                "stage=" + stage +"\n"+
                "date and time=" + ldt +"\n"+
                "car number=" + carNumber+"\n";
    }
}
