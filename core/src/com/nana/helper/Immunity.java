package com.nana.helper;

import java.util.Timer;
import java.util.TimerTask;

public class Immunity {
    private boolean immune;
    private Timer immunityTimer;

    /**
     * initially immune is set to false
     */
    public Immunity() {
        immune = false;
    }

    /**
     * give immunity for 3 seconds if called
     */
    public void giveImmunity() {
        immune = true;
        immunityTimer = new Timer();
        immunityTimer.schedule(new ImmunityTask(), 3000);
    }

    /**
     * @return true or false whether player is immune
     */
    public boolean isImmune() {
        return immune;
    }

    private class ImmunityTask extends TimerTask {
        @Override
        public void run() {
            immune = false;
            immunityTimer.cancel();
        }
    }
}
