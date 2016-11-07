package com.sher.tool.base.test.thread;

import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2016/10/26.
 *
 *  reference : http://www.cnblogs.com/zhuawang/p/4196904.html
 *              http://blog.csdn.net/qq_16811963/article/details/52171764
 *              http://www.liuinsect.com/2014/01/27/how_to_understand_condition/
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
