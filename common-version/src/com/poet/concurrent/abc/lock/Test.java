package com.poet.concurrent.abc.lock;

import com.poet.concurrent.abc.BaseAbcThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poet
 */
public class Test {


    public static void main(String[] args) {

        final Lock lock = new ReentrantLock();
        final Condition conA = lock.newCondition();
        final Condition conB = lock.newCondition();
        final Condition conC = lock.newCondition();
        final Integer printCount = 10;

        final LockMonitor monitorA = new LockMonitor(conA,true);
        final LockMonitor monitorB = new LockMonitor(conB);
        final LockMonitor monitorC = new LockMonitor(conC);

        BaseAbcThread aThread = new LockBasedAbcPrintThread(printCount, "A", lock, monitorA, monitorB);
        BaseAbcThread bThread = new LockBasedAbcPrintThread(printCount, "B", lock, monitorB, monitorC);
        BaseAbcThread cThread = new LockBasedAbcPrintThread(printCount, "C", lock, monitorC, monitorA);

        aThread.start();
        bThread.start();
        cThread.start();

    }

}
