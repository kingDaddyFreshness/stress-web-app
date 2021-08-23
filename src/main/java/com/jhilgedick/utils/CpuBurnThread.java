package com.jhilgedick.utils;

public class CpuBurnThread extends Thread {
    
    private double load;
    private long durationMs;

    public CpuBurnThread(String name, double load, long durationMs) {
        super(name);
        this.load = load;
        this.durationMs = durationMs;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            // Loop for the given duration
            while (System.currentTimeMillis() - startTime < durationMs) {
                // Every 100ms, sleep for the percentage of unladen time
                if (System.currentTimeMillis() % 1000 == 0) {
                    Thread.sleep((long) Math.floor((1 - load) * 100));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public void burn(Integer threadCount, Double load, Long durationMS) {
        for (int i = 0; i < threadCount; i++) {
            new CpuBurnThread("Thread" + i, load, durationMS).start();
        }
    }
}
