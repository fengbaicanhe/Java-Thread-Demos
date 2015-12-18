package com.poet.concurrent.pc.lock;

import com.poet.concurrent.pc.AbstractPcThread;
import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.Queue;

/**
 * @author poet
 */
public class Consumer extends AbstractPcThread {

    private LockSharedObject shared ;

    protected Consumer(AbstractSharedObject sharedObject, String name) {
        super(sharedObject, name);
    }

    @Override
    protected void init() {
        this.shared = (LockSharedObject) super.sharedObject;
    }

    @Override
    protected void doAction() {
        Queue<String> queue = shared.getQueue();
        while (!this.isInterrupted()) {

            String data;
            while ((data = queue.poll()) == null) {
                shared.lock.lock();
                try {
                    System.out.println("consumer " + name + " : The queue is empty!");
                    shared.emptyCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    shared.lock.unlock();
                }
            }

            System.out.println("consumer " + name + " consumed " + data);

            try {
                shared.lock.lock();
                shared.fullCon.signalAll();
            } finally{
                shared.lock.unlock();
            }
        }
    }
}
