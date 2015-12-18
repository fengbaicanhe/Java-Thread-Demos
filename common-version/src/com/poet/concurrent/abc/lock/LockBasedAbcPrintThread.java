package com.poet.concurrent.abc.lock;

import com.poet.concurrent.abc.BaseAbcThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockBasedAbcPrintThread extends BaseAbcThread {


    private final Lock lock;
    private final LockMonitor currentMonitor;
    private final LockMonitor nextMonitor;

    public LockBasedAbcPrintThread(Integer printCount, String printStr, Lock lock, LockMonitor currentMonitor, LockMonitor nextCondition) {
        super(printCount,printStr);
        this.lock = lock;
        this.currentMonitor = currentMonitor;
        this.nextMonitor = nextCondition;
    }

    @Override
    protected void doPrint() {
        while( !Thread.currentThread().isInterrupted() && !currentMonitor.canRun ){
            try {
                lock.lock();
                currentMonitor.condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        nextMonitor.canRun = true;
        currentMonitor.canRun = false;
        System.out.println(getPrintStr());
        try {
            lock.lock();
            nextMonitor.condition.signal();
        } finally {
            lock.unlock();
        }

    }
}
