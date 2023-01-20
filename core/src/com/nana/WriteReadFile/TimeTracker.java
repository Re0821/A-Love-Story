package com.nana.WriteReadFile;

public class TimeTracker {
    private static TimeTracker instance = null;
    private long startTime;
    private long endTime;
    private long totalTime;
    private int timeInSeconds;

    public TimeTracker() {}

    /**
     * @return the current class that it is running from
     */
    public static TimeTracker getInstance() {
        if (instance == null) {
            instance = new TimeTracker();
        }
        return instance;
    }

    /**
     * start the timer in milliseconds
     */

    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * ends the time in milliseconds
     */

    public void stop() {
        endTime = System.currentTimeMillis();

    }

    /**
     * @return the time in second after conversion
     */
    
    public int getTimeTaken() {
        totalTime = endTime - startTime;
        timeInSeconds = Math.abs((int)totalTime / 1000);
        return timeInSeconds;
    }
}
