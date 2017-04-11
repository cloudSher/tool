package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 * 任务类
 */
public class Task implements Runnable {


    /**
     * 具体业务类
     */
    private Service service;

    Task(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println(" Task: -- " + Thread.currentThread().getName()+" 线程正在执行 ... --- service : " + service);
        service.doRun();
    }
}
