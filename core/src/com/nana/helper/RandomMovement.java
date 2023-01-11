package com.nana.helper;

import java.util.Random;

public class RandomMovement {
    private int switchDirection;
    private Random random;

    public RandomMovement(){
        random = new Random();
    }
    private void generateRandomMovement(){
        switchDirection = random.nextInt(3);
        while(switchDirection <= 0 ){
            switchDirection = random.nextInt(3);
        }
    }
    public int getSwitchDirection() {
        switchDirection = 2;
        return switchDirection;
    }

    
}   
