package com.sher.tool.base.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ForkJoinDemo {


    public static void main(String args[]){
        ForkJoinPool pool = new ForkJoinPool();
//        ForkJoinTask<?> task = pool.submit(new Task());
        ForkJoinTask<Integer> task = pool.submit(new ForkTask(2));
        try {
            Object o = task.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }


    static class Task extends RecursiveTask{

        private AtomicInteger num = new AtomicInteger(0);
        private int n = 0;

        @Override
        protected Object compute() {
            while(n < 1000){
//                num.getAndIncrement();
                n++;
            }
            return n;
        }
    }

    /**
     * fork 拆分任务，join  合并任务解
     */
    static class ForkTask extends RecursiveTask<Integer>{
        private int n;

        ForkTask(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if(n <= 1){
                return run(n);
            }else{
                ForkTask task = new ForkTask(n - 1);
                task.fork();
                ForkTask task1 = new ForkTask(n - 2);
                task1.fork();
                int sum = task.join() + task1.join();
                return sum;
            }
        }

        public int run(int n){
            return n;
        }
    }


}
