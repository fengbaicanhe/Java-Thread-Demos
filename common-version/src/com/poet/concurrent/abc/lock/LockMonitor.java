package com.poet.concurrent.abc.lock;

import java.util.concurrent.locks.Condition;

/**
 * @author poet
 */
public class LockMonitor {

    volatile  boolean canRun = false;
    Condition condition;

    public LockMonitor(Condition condition){
        this.condition = condition;
    }

    public LockMonitor(Condition condition,boolean canRun) {
        this(condition);
        this.canRun = canRun;
    }
}
