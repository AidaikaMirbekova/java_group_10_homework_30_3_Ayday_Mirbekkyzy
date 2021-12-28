package com.company;

import java.util.Random;

public enum State {
    DRIVING(3),
    PARKING(3);

    private final Random rnd=new Random();
    private final int shanceParking;

    State(int shanceParking) {
        this.shanceParking = shanceParking;
    }

    public boolean changeState(){
        return rnd.nextInt(1000)<(shanceParking/100.d)*1000.d;
    }
}
