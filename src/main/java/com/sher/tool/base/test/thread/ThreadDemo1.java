package com.sher.tool.base.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/10/26.
 *
 *  交替打印数字
 */
public class ThreadDemo1 {

    static class Action implements Runnable{

        int num;
        int time;
        volatile int count = 0;
        private Object lock = new Object();

        Action(int num,int time,int flag){
            this.num = num;
            this.time = time;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < time; i++){
                synchronized (lock){
                    for(int j = 0 ; j < num; j++){
                        System.out.println(Thread.currentThread().getName()+":" +(++count));
                    }
                    lock.notify();              //唤醒等待线程
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 }
             }
        }
    }

    public static void main(String args[]){
        method3();
    }

    public static void method1(){
        Action action = new Action(3,5,0);
        Thread at = new Thread(action);
        Thread bt = new Thread(action);
        at.start();
        bt.start();
    }

    /**
     * 拆分成多个不同任务，通过切换任务的状态来执行
     */
    public static void method2(){
        class Internal{
            private int count = 0;
            Object lock = new Object();
            int state = 1;
            public void action(){
                new Thread(()->{
                    for(int i = 0; i < 5; i++){
                        synchronized (lock){
                            while(state != 1){
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for(int j = 0 ; j < 3; j++){
                                System.out.println(Thread.currentThread().getName()+":"+(++count));
                            }
                            state = 2;
                            lock.notify();
                        }
                    }
                }).start();
                new Thread(()->{
                    for(int i = 0 ; i < 5; i++){
                        synchronized (lock){
                            while(state != 2){
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for(int j = 0 ; j < 3; j++){
                                System.out.println(Thread.currentThread().getName()+":"+(++count));
                            }
                            state = 1;
                            lock.notify();
                        }
                    }
                }).start();
            }

        }
        new Internal().action();
    }


    public static void method3(){
        class IntelLock{
            private int count = 0;
            private Lock lock = new ReentrantLock();
            private int state = 1;
            private Condition cond1 = lock.newCondition();
            private Condition cond2 = lock.newCondition();

            public void action(){
                new Thread(()->{
                    for(int i = 0 ; i < 5; i++){
                        try {
                            lock.lock();
                            while(state != 1){
                                cond1.await();
                            }
                            for(int j = 0 ; j < 3; j++){
                                System.out.println(Thread.currentThread().getName()+":"+(++count));
                            }
                            state = 2;
                            cond2.signal();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }).start();

                new Thread(()->{
                    for(int i = 0; i < 5; i++){
                        try{
                            lock.lock();
                            while(state != 2){
                                cond2.await();
                            }
                            for(int j = 0 ; j < 3 ;j++){
                                System.out.println(Thread.currentThread().getName()+":"+(++count));
                            }
                            state = 1;
                            cond1.signal();
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }).start();
            }

        }
        new IntelLock().action();
    }




}
