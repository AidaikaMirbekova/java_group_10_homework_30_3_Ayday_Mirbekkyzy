package com.company;

import java.time.LocalDateTime;
import java.util.*;

public class Simulation {
    private final Set<Car> cars;
    private final List<Parking> parkings;
    private final int days;
    private final int step;

    public Simulation(Builder builder) {
        this.parkings=builder.parking;
        this.step=builder.stepMinutes;
        this.cars=initCars(builder.carAmount);
        this.days= builder.days;

    }

    public static Builder builder(){
        return new Builder();
    }

    private Set<Car> initCars(int amountCar){
        Set<Car> set=new HashSet<>();
        for (int i = amountCar; i >0 ; i--) {
          Car car = new Car();
            set.add(car);
        }
        return set;
    }

    private Parking getRandomParking(){
        Collections.shuffle(parkings);
        return parkings.get(0);
    }

    public List<Event> run(){
        LocalDateTime current=LocalDateTime.now().withMinute(0);
        LocalDateTime stage = current.plusDays(days);
        while (current.isBefore(stage)){
            current = current.plusMinutes(5);
            for (Car c:cars) {
                if (c.willChangeState()){
                    c.changeState(c,current,getRandomParking());
                }
            }
        }
        List<Event> events = new ArrayList<>();
        for (Parking p:parkings){
            for (List<Event> e:p.getEvents().values()) {
                events.addAll(e);
            }
        }
        return events;
    }

    public static final class Builder{
        private final List<Parking> parking=new ArrayList<>();
        private int stepMinutes=5;
        private int days=30;
        private int carAmount=50;

        public Builder days(int days){
            this.days=days;
            return this;
        }

        public Builder step(int stepMinutes){
            this.stepMinutes=stepMinutes;
            return this;
        }

        public Builder carAmount(int carAmount){
            this.carAmount=carAmount;
            return this;
        }

        public Builder addParking(int maxPlaceParking){
            parking.add(Parking.makePlaceOfParking(maxPlaceParking));
            return this;
        }

        public Simulation build(){
            return new Simulation(this);
        }
    }

}
