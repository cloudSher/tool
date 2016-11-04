package com.sher.tool.base.test.thread;

/**
 * Created by Administrator on 2016/11/4.
 */
public class DeadLock {


    public static void main(String args[]){
        Object lock = new Object();
        Object lock1 = new Object();
        new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    while(true)
                        System.out.println("lock1");
                }
            }
        }).start();
        new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    while (true)
                        System.out.println("lock");
                }
            }
        }).start();
    }
}
