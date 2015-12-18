package com.poet.concurrent.pc.lock;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author  poet
 */
public class Test {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        final Integer queueLimit = 10;
        final Integer produceCount = 100;

        LockSharedObject shared = new LockSharedObject(queue, queueLimit, produceCount);

        Producer producer = new Producer(shared, "生产者Z");
        Consumer ca = new Consumer(shared,"消费者A");
        Consumer cb = new Consumer(shared,"消费者B");
        Consumer cc = new Consumer(shared,"消费者C");


        ca.start();
        cb.start();
        cc.start();
        producer.start();

    }


}
