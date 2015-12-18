package com.poet.concurrent.pc;

/**
 * @author poet
 */
public abstract class AbstractPcThread extends Thread {

    protected final AbstractSharedObject sharedObject;
    protected final String name;

    protected AbstractPcThread(AbstractSharedObject sharedObject, String name) {
        this.sharedObject = sharedObject;
        this.name = name;
    }

    @Override
    public void run() {
        init();
        doAction();
    }

    protected abstract void init();

    protected abstract void doAction();

}
