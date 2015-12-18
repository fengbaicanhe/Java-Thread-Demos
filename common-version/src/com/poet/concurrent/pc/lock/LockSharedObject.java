package com.poet.concurrent.pc.lock;

import com.poet.concurrent.pc.AbstractSharedObject;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poet
 */
public class LockSharedObject extends AbstractSharedObject {

    Lock lock = new ReentrantLock();
    Condition fullCon = lock.newCondition();
    Condition emptyCon = lock.newCondition();

    public LockSharedObject(Queue<String> queue, Integer queueLimit, Integer produceCount) {
        super(queue, queueLimit, produceCount);
    }

}