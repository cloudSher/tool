package com.sher.tool.base.test.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier barrier = new CyclicBarrier(5);


    public static void main(String args[]){
        for(int i = 0 ;i < 5; i++){
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println( Thread.currentThread().getName() + " thread already start ....");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() +"  : ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("main run end ...");
    }
}
