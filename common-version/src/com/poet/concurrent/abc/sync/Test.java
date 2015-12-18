package com.poet.concurrent.abc.sync;

import com.poet.concurrent.abc.BaseAbcThread;

/**
 * @author
 */
public class Test {

    public static void main(String[] args) {

        final Integer printCount = 10;
        final SyncMonitor syncMonitorA = new SyncMonitor(true);
        final SyncMonitor syncMonitorB = new SyncMonitor();
        final SyncMonitor syncMonitorC = new SyncMonitor();

        BaseAbcThread printAThread = new SyncBasedAbcPrintThread("A", syncMonitorA, syncMonitorB,printCount);
        BaseAbcThread printBThread = new SyncBasedAbcPrintThread("B", syncMonitorB, syncMonitorC,printCount);
        BaseAbcThread printCThread = new SyncBasedAbcPrintThread("C", syncMonitorC, syncMonitorA,printCount);

        printAThread.start();
        printBThread.start();
        printCThread.start();

    }


}
