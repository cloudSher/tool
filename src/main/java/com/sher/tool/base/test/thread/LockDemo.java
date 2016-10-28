package com.sher.tool.base.test.thread;

import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2016/10/26.
 */
public class LockDemo {


    public static void readAndWriteLock(){
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        readLock.lock();
        //
        Condition c1 = readLock.newCondition();
        readLock.unlock();
    }
}
