package com.poet.concurrent.pc.sync;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author poet
 */
public class PcSyncTest {


    @Test
    public void test(){
        Queue<String> queue = new LinkedList<>();
        final Integer queueLimit = 10;
        final Integer produceCount = 100;
        SyncSharedObject shared = new SyncSharedObject(queue, queueLimit, produceCount);
        Producer producer = new Producer(shared, "生产者Z");
        Consumer ca = new Consumer(shared,"消费者A");
        Consumer cb = new Consumer(shared,"消费者B");
        Consumer cc = new Consumer(shared,"消费者C");

        producer.start();
        ca.start();
        cb.start();
        cc.start();
    }

}
