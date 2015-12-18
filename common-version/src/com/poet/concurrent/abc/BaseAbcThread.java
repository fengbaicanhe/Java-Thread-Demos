package com.poet.concurrent.abc;

/**
 * @author poet
 */
public abstract class BaseAbcThread extends Thread {

    private Integer printCount;

    private String printStr;

    public BaseAbcThread(Integer printCount, String printStr) {
        this.printCount = printCount;
        this.printStr = printStr;
    }

    protected Integer getPrintCount(){
        return printCount;
    }

    protected String getPrintStr(){
        return printStr;
    }

    @Override
    public void run() {
        for (Integer i = 0; i < printCount; i++) {
            doPrint();
        }
    }

    protected abstract void doPrint();

}
