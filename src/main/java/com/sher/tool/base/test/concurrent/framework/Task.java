package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 * 任务类
 */
public class Task implements Runnable {


    private Service service;

    Task(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println(" -- " + Thread.currentThread().getName()+" 线程正在执行 ...");
        service.doRun();
    }
}
