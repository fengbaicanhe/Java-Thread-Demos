package com.poet.concurrent.abc.sync;

/**
 * @author poet
 */
public class SyncMonitor {

    volatile boolean canRun = false;

    public SyncMonitor(){}

    public SyncMonitor(boolean canRun) {
        this.canRun = canRun;
    }
}
