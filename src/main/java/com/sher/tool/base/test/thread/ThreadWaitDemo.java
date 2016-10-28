package com.sher.tool.base.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ThreadWaitDemo {

//    private Lock lock = new ReentrantLock();
    private static Object obj = new Object();

    public static void main(String args[]){
//        waitTest();
        testCountDown();
    }

    /***
     * test ,thread if wait, current Thread will go on run code when notify after wait;
     */
    public static void waitTest(){
        new Thread(()->{
            while(true){
                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" after wait ....");
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj){
            obj.notify();
            System.out.println(Thread.currentThread().getName()+", after notify()");
        }
        System.out.println("main thread is run, after notify()");
    }


    static class CountDownTest{
        private CountDownLatch latch = new CountDownLatch(3);

        public void run(){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+": running ....");
                latch.countDown();
            }).start();

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+": running ...");
                latch.countDown();
            }).start();

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+": running ...");
                latch.countDown();
            }).start();

            System.out.println("main Thead is runing ....");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main Thread is runned");
        }

    }


    public static void testCountDown(){
        new CountDownTest().run();
    }
}
