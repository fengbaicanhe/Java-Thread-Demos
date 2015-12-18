package com.poet.concurrent.pc.sync;

import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.Queue;

/**
 * @author poet
 */
public class SyncSharedObject extends AbstractSharedObject {

    final Object fullMonitor = new Object();
    final Object emptyMonitor = new Object();

    public SyncSharedObject(Queue<String> queue, Integer queueLimit, Integer produceCount) {
        super(queue, queueLimit, produceCount);
    }

}
