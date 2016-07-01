package com.sher.tool.base.test.thread;

/**
 * Created by cloudsher on 2016/6/3.
 */
public class ConcurrentDemo {


    public static void main(String args[]){
        Task task = new Task();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }

    static class Task implements Runnable{
        int num = 0;
        @Override
        public void run() {
            while (num ++ < 100)
                System.out.println("currrent:" +Thread.currentThread().getName()+"========"+ num);
        }
    }
}
