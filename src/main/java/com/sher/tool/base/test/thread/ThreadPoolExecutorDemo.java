package com.sher.tool.base.test.thread;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.*;

/**
 * Created by cloudsher on 11/16/2016.
 */
public class ThreadPoolExecutorDemo {

    private static ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    private static BlockingQueue queue = new LinkedBlockingDeque<>(1000);
    private static boolean isClose = false;

    /**
     * 模拟生产者，每200ms产生数据，放入到queue
     */
    public static void produce(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(()->{
            int n = 0;
            queue.offer(" " + n++);
        },200,TimeUnit.MILLISECONDS);
        service.getTaskCount();
    }

    /***
     * 模拟消费者，每1秒消费队列一条消息，
     */
    public static void consume(){
        while(!isClose){
            getTaskCount();
            if(!service.isShutdown())
            service.submit(()->{
                try {
                    queue.take();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static {
        Signal signal = new Signal("USER2");
        Signal.handle(signal, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println("accect kill signal ...");
                //关闭订阅消费
                isClose = true;
                //关闭线程池
                service.shutdown();
                //判断线程是否关闭
                while(!service.isTerminated()){
                    //每200ms，判断线程池积压数量
                    getTaskCount();
                    try {
                        TimeUnit.MILLISECONDS.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("订阅者关闭，线程池关闭");
                System.exit(0);
            }
        });
    }

    public static long getTaskCount(){
        long count = service.getTaskCount() - service.getCompletedTaskCount();
        System.out.println("current task count :" + count);
        return count;
    }

    public static void main(String args[]){
        produce();
        consume();
    }





}
