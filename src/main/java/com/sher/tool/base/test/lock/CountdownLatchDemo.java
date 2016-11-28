package com.sher.tool.base.test.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CountdownLatchDemo {


    private static CountDownLatch latch = new CountDownLatch(10);

    public static void main(String args[]) throws InterruptedException {
        for(int i = 0 ; i < 10; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" : ");
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("main run end ...");
    }
}
