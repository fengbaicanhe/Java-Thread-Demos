package com.poet.concurrent.abc.pool;

import com.poet.concurrent.abc.BaseAbcThread;

/**
 *
 * @author
 */
public class PooledAbcPrintThread extends BaseAbcThread {

    public PooledAbcPrintThread(String printStr){
        super(0,printStr);
    }

    @Override
    public void run() {
        System.out.println(super.getPrintStr());
    }

    @Override
    protected void doPrint() {
        // do nothing
    }
}
