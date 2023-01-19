package com.nana.WriteReadFile;

import java.util.concurrent.TimeUnit;

public class TimeTracker {
    private static TimeTracker instance = null;
    private long startTime;
    private long endTime;
    private long totalTime;
    private int timeInSeconds;

    public TimeTracker() {}

    public static TimeTracker getInstance() {
        if (instance == null) {
            instance = new TimeTracker();
        }
        return instance;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();

    }

    public int getTimeTaken() {
        totalTime = endTime - startTime;
        timeInSeconds = Math.abs((int)totalTime % 100);
        return timeInSeconds;
    }
}
