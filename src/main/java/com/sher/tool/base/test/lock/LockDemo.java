package com.sher.tool.base.test.lock;

import java.util.concurrent.TimeUnit;
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


    /***
     * 可重入锁，可轮训，定时锁，可中断
     */
    public static void reentrant(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        long nanoTime = System.nanoTime();
        int state = 0;
        while (true){
            try {
                lock.tryLock(nanoTime+100, TimeUnit.NANOSECONDS);

                if(state == 1){
                    condition.await();
                }
                for(int i = 0 ;i < 10; i++){
                    System.out.println("curr i : " + i +"lock:"+lock);
                }
                state = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrup(lock);
            }finally {
                lock.unlock();
            }
        }
    }

    public static void interrup(Lock lock){
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        reentrant();
    }
}
