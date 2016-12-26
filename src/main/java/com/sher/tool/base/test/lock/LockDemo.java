package com.sher.tool.base.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2016/10/26.
 *
 *  reference : http://www.cnblogs.com/zhuawang/p/4196904.html
 *              http://blog.csdn.net/qq_16811963/article/details/52171764
 *              http://www.liuinsect.com/2014/01/27/how_to_understand_condition/
 *              http://www.open-open.com/lib/view/open1352431526366.html
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

    /***
     *  测试lock的中断线程
     *
     * @throws InterruptedException
     */
    public static void interrupt() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Run run = new Run(lock);
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);

        t1.start();
        t2.start();

        Thread.sleep(2000);
        t1.interrupt();  //中断t2线程
        System.out.println("======= main end ...");
    }

    static class Run implements Runnable{

        private int num = 0;
        private Lock lock;

        Run(Lock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            try{
                lock.lockInterruptibly();   //只会中断等待的线程，可中断的锁请求
                for(;;){
                    System.out.println(Thread.currentThread().getName() +" num is :" + num++);
                    Thread.sleep(1000);   //可阻塞情况
                }
            }catch (Exception e){
                System.out.println(Thread.currentThread().getName() + "被中断");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() +" 锁被释放 。。。");
            }
        }

        /***
         * sleep,wait,join 方法的时候，取消阻塞，重置状态位，抛出异常
         */
        public void block(){

        }
    }

    public static void main(String args[]) throws InterruptedException {
//        reentrant();
        interrupt();
    }
}
