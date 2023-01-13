package com.nana.helper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomMovement {
    private int randomNumber;
    private Timer timer;
    
    public RandomMovement() {
        timer = new Timer();
        timer.schedule(new RandomNumberTask(), 0, 1000);
    }
    
    public int getRandomNumber() {
        return randomNumber;
    }
    
    private class RandomNumberTask extends TimerTask {
        @Override
        public void run() {
            Random rand = new Random();
            randomNumber = rand.nextInt(3);
            while(randomNumber == 0){
                randomNumber = rand.nextInt(3);
            }
        }
    }
}
