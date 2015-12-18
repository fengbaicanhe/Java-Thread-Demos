package com.poet.concurrent.abc.sync;

import com.poet.concurrent.abc.BaseAbcThread;

/**
 * @author poet
 */
public class SyncBasedAbcPrintThread extends BaseAbcThread {

    private final SyncMonitor currentSyncMonitor;
    private final SyncMonitor nextSyncMonitor;

    public SyncBasedAbcPrintThread(String printStr, SyncMonitor currentSyncMonitor, SyncMonitor nextSyncMonitor, Integer printCount) {
        super(printCount,printStr);
        this.currentSyncMonitor = currentSyncMonitor;
        this.nextSyncMonitor = nextSyncMonitor;
    }

    @Override
    protected void doPrint() {
        while (!Thread.currentThread().isInterrupted() && !currentSyncMonitor.canRun) {
            synchronized (currentSyncMonitor) {
                try {
                    currentSyncMonitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        nextSyncMonitor.canRun = true;
        currentSyncMonitor.canRun = false;
        System.out.println(getPrintStr());

        synchronized (nextSyncMonitor){
            nextSyncMonitor.notify();
        }
    }
}
