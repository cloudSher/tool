package com.sher.tool.base.test.concurrent.framework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 * 多线程上下文，管理线程池
 */
public class ExecutorContext {


    private ExecutorService executorService;

    public static ExecutorContext newInstance(){
        return new ExecutorContext();
    }

    public void init(){
        executorService = Executors.newFixedThreadPool(10);
    }


    public Object execute(Runnable task) throws ExecutionException, InterruptedException {
        Future<?> submit = executorService.submit(task);
        return null;
    }
}
