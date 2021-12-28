package com.company;

import java.time.LocalDateTime;
import java.util.*;

public class Parking {
    private final Set<Car> parkingCar=new HashSet<>();
    private final Map<Car, List<Event>> events = new HashMap<>();
    private final int check;

    public Parking(int check) {
        this.check = check;
    }

    public Map<Car, List<Event>> getEvents() {
        return events;
    }

    public static Parking makePlaceOfParking(int maxPlace){
        return new Parking(maxPlace);
    }

    public boolean isFreePlace(){
        return parkingCar.size()!=check;
    }

    public boolean park(Car car, LocalDateTime ldt){
        if (!isFreePlace())return false;
        System.out.println(isFreePlace());
        if (parkingCar.contains(car)){return false;}
        parkingCar.add(car);
        List<Event> eventCar=events.computeIfAbsent(car,k->new ArrayList<>());
//        List<Event> eventCar=events.get(car);
//        if (eventCar==null){
//            eventCar=new ArrayList<>();
//            events.put(car,eventCar);
//        }
        eventCar.add(Event.registerEnter(ldt,car));
        return true;
    }

    public void parkedCarPlace(Car car,LocalDateTime ldt){
        if (parkingCar.contains(car)) {
            events.get(car).add(Event.registerExit(ldt,car));
            parkingCar.remove(car);
        }
    }


}
