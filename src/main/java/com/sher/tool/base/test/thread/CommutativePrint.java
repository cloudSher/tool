package com.sher.tool.base.test.thread;

/**
 * Created by wei.zhao on 2017/3/24.
 */
public class CommutativePrint {


    public static void main(String args[]){
        Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                for(int i = 0 ; i < 100; i++){
                    if(i % 2 == 0){
                        System.out.println("num : " + i);
                        lock.notifyAll();

                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();

        new Thread(()->{
            synchronized (lock){
                for(int i  = 0 ;i < 100; i++){
                    if( i % 2 == 1){
                        System.out.println("num : " + i);
                        lock.notifyAll();
                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}
