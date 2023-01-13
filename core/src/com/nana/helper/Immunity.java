package com.nana.helper;

import java.util.Timer;
import java.util.TimerTask;

public class Immunity {
    private boolean immune;
    private Timer immunityTimer;

    public Immunity() {
        immune = false;
    }

    public void giveImmunity() {
        immune = true;
        immunityTimer = new Timer();
        immunityTimer.schedule(new ImmunityTask(), 3000);
    }

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
