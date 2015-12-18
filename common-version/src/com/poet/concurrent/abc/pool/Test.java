package com.poet.concurrent.abc.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author poet
 */
public class Test {


    public static void main(String[] args) {
        String[] printStrs = {"A", "B", "C"};
        final Integer printCount = 10;
        final Integer computedCount = 10 * printStrs.length;

        ExecutorService exec = Executors.newSingleThreadExecutor();

        for (Integer i = 0; i < computedCount; i++) {
            Integer ix = i % 3;
            exec.execute(new PooledAbcPrintThread(printStrs[ix]));
        }


    }


}

