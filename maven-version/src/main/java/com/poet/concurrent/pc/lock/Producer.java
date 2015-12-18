package com.poet.concurrent.pc.lock;

import com.poet.concurrent.pc.AbstractPcThread;
import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.Queue;

/**
 * @author poet
 */
public class Producer extends AbstractPcThread {

    private LockSharedObject shared;

    protected Producer(AbstractSharedObject sharedObject, String name) {
        super(sharedObject, name);
    }

    @Override
    protected void init() {
        this.shared = (LockSharedObject) super.sharedObject;
    }

    @Override
    protected void doAction() {
        Queue<String> queue = shared.getQueue();
        for (Integer i = 0; i < shared.getProduceCount(); i++) {
            if (shared.isQueueFull()) {
                shared.lock.lock();
                try {
                    System.out.println("The producer " + super.name + " : The queue is full!");
                    shared.fullCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    shared.lock.unlock();
                }
            }
            String data;

            shared.lock.lock();
            data = produceData(i);
            queue.add(data);
            shared.lock.unlock();

            System.out.println("producer " + super.name + " produced " + data);

            try {
                shared.lock.lock();
                shared.emptyCon.signalAll();
            } finally{
                shared.lock.unlock();
            }
        }
    }

    private String produceData(Integer i) {
        return "Line #" + i;
    }
}
