package com.poet.concurrent.abc.pool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author poet
 */
public class AbcPoolTest {


    @Test
    public void test(){
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
