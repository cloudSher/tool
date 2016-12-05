package com.sher.tool.trace;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ThreadDemo {


    public static void main(String args[]) throws InterruptedException {
        threadSleep(2000 * 10);
        new ThreadDemo().threadCount(10);
    }

    public  void threadCount(int tn) throws InterruptedException {
        for(int i = 0 ; i< tn; i++){
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ... end");
            }).start();
        }
    }

    public static void threadSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Object obj = new Object();
    private Integer integer = new Integer(1);
    private  static int status = 0;

    public static void threadLock(){
        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + " acquire lock");
                if(status == 0){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    status = 1;
                }
            }
        }).start();

        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + " acquire lock");
                if(status == 1){
                    obj.notify();
                }
            }
        }).start();

    }

    public void sync(){
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            notify();
        }
    }
}
