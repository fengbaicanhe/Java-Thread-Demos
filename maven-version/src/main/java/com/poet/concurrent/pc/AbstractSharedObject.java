package com.poet.concurrent.pc;

import java.util.Queue;

/**
 * Created by Administrator on 15-12-17.
 */
public class AbstractSharedObject {

    final Queue<String> queue;
    final Integer queueLimit;
    final Integer produceCount;

    public AbstractSharedObject(Queue<String> queue, Integer queueLimit, Integer produceCount) {
        this.queue = queue;
        this.queueLimit = queueLimit;
        this.produceCount = produceCount;
    }

    public Queue<String> getQueue() {
        return queue;
    }


    public boolean isQueueEmpty(){
        return queue.isEmpty();
    }

    public boolean isQueueFull(){
        return queue.size() == queueLimit;
    }

    public Integer getQueueLimit() {
        return queueLimit;
    }

    public Integer getProduceCount() {
        return produceCount;
    }
}
