package com.sher.tool.base.test.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/11/10.
 *
 * semaphore concurrent how many thread run
 */
public class SemaphoreDemo {


    private Semaphore semaphore = new Semaphore(5);
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static CountDownLatch start = new CountDownLatch(1);


    public void run(Runnable run){
        try {
            semaphore.acquire();
            System.out.println("num is : " + Model.num);
            executorService.submit(run);

        } catch (InterruptedException e) {
            e.printStackTrace();
            semaphore.release();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String args[]){
        threadRun(10);
        start.countDown();
    }

    public static void mainRun(){
        SemaphoreDemo d = new SemaphoreDemo();
        for(int i = 0 ; i < 10; i++)
            d.run(()-> {Model.num++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run num:" + Model.num);
            });
        System.out.println(Model.num);
    }

    public static void threadRun(int n){
        SemaphoreDemo d = new SemaphoreDemo();
        for(int i = 0 ; i < n;i++){
            new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.run(()->{
                    Model.num++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("run num:" + Model.num);
                });
            }).start();
        }
    }

    static class Model{
        public static int num = 0;
    }

}
