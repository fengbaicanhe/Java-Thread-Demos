package com.poet.concurrent.pc.sync;

import com.poet.concurrent.pc.AbstractPcThread;
import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.Queue;

/**
 * @author poet
 */
public class Producer extends AbstractPcThread {

    private SyncSharedObject shared ;
    protected Producer(AbstractSharedObject sharedObject, String name) {
        super(sharedObject, name);
    }

    @Override
    protected void init() {
        this.shared = (SyncSharedObject) super.sharedObject;
    }

    @Override
    protected void doAction() {
        Queue<String> queue = shared.getQueue();
        for (Integer i = 0; i < shared.getProduceCount(); i++) {
            if( shared.isQueueFull() ){
                synchronized (shared.fullMonitor){
                    try {
                        System.out.println("The producer " + super.name + " : The queue is full!");
                        shared.fullMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            String data;
            synchronized (queue){
                data = produceData(i);
                queue.add(data);
            }
            System.out.println("producer " + super.name + " produced " + data);
            synchronized (shared.emptyMonitor){
                shared.emptyMonitor.notifyAll();
            }

        }
    }

    private String produceData(Integer i) {
        return "Line #" + i;
    }
}
