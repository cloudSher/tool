package com.sher.tool.base.test.thread;

/**
 * Created by cloudsher on 2016/8/5.
 */
public class ThreadDemo {



    static class ThreadA implements Runnable{

        String name;
        Object pre;
        Object self;

        ThreadA(String name,Object pre,Object self){
            this.name = name;
            this.pre= pre;
            this.self = self;
        }

        @Override
        public void run() {
            while(true){
                synchronized (pre){
                    synchronized (self){
                        System.out.print(name);
                        self.notifyAll();
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable{

        String name;
        Object pre;
        Object self;

        ThreadB(String name,Object pre,Object self){
            this.name = name;
            this.pre = pre;
            this.self = self;
        }

        @Override
        public void run() {
            while (true){
                synchronized (pre){
                    synchronized (self){
                        System.out.print(name);
                        self.notifyAll();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ThreadC implements Runnable{

        String name;
        Object lock;
        int num;

        ThreadC(String name,int num,Object lock){
            this.name = name;
            this.num = num;
            this.lock = lock;
        }

        @Override
        public void run() {
            while(true){
                synchronized (lock){
                    if(num>0){
                        System.out.println(name);
                        num--;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify();
                }
            }
        }
    }

    static class ThreadD implements Runnable{

        String name;
        Object lock;
        int num;

        ThreadD(String name,int num,Object lock){
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            while(true){
                synchronized (lock){
                    if(num == 0){
                        num++;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(name);
                    lock.notify();
                    System.out.println("threadD is running");
                }
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Object pre = new Object();
        Object self = new Object();
        Thread threadA = new Thread(new ThreadA("a",pre,self));
        Thread threadB = new Thread(new ThreadB("b",self,pre));
//        threadA.start();
//        threadB.start();
        Thread threadC = new Thread(new ThreadC("a",1,pre));
        threadC.start();
        threadC.sleep(1000);
        new Thread(new ThreadD("b",1,pre)).start();
    }
}
