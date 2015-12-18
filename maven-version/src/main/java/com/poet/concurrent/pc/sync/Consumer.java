package com.poet.concurrent.pc.sync;

import com.poet.concurrent.pc.AbstractPcThread;
import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author poet
 */
public class Consumer extends AbstractPcThread {

    private SyncSharedObject shared;

    public Consumer(AbstractSharedObject sharedObject, String name) {
        super(sharedObject, name);
    }

    @Override
    protected void init() {
        this.shared = (SyncSharedObject)super.sharedObject;
    }

    @Override
    protected void doAction() {
        Queue<String> queue = shared.getQueue();
        while (!this.isInterrupted()) {

            String data;
            while ((data = queue.poll()) == null) {
                synchronized (shared.emptyMonitor) {
                    try {
                        System.out.println("consumer " + name + " : The queue is empty!");
                        shared.emptyMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("consumer " + name + " consumed " + data);
            synchronized (shared.fullMonitor) {
                shared.fullMonitor.notifyAll();
            }

        }
    }
}
