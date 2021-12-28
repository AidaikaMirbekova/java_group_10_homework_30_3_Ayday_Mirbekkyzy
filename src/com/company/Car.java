package com.company;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {
    private String numbersOfCar;
    private State state;
    private Parking parkingCar;

    public Car() {
        this.numbersOfCar=Numbers.number();
        this.state = State.DRIVING;
    }

    public String getNumbersOfCar() {
        return numbersOfCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getNumbersOfCar(), car.getNumbersOfCar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumbersOfCar());
    }

    public void park(LocalDateTime ldt,Parking parkingCar){
        this.parkingCar=parkingCar;
        if (parkingCar.park(this,ldt)){
            state=State.PARKING;
        }
    }

    public boolean parking(){
        return state==State.PARKING;
    }

    public boolean willChangeState(){
        return state.changeState();
    }

    public void changeState(Car car,LocalDateTime ldt,Parking parkingCar){
        if (parking()){
            car.driving(ldt);
        }else {
            park(ldt,parkingCar);
        }
    }

    public void driving(LocalDateTime ldt){
        parkingCar.parkedCarPlace(this,ldt);
        state=State.DRIVING;
    }

    @Override
    public String toString() {
        return "Car:" +
                "number of car=" + numbersOfCar+"\n"+
                "state=" + state +"\n"+
                "parking car=" + parkingCar +"\n";
    }
}
