package com.sher.tool.base.test.concurrent.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by wei.zhao on 2017/3/25.
 */
public class Main {


    public static void main(String args[]){
        ExecutorContext context = ExecutorContext.newInstance();
        context.init();
        start(context);

    }

    public static Service createService(int num){
        Service service = new ServiceImpl(num);
        return service;
    }

    /**
     * 模拟用户创建过个任务
     * @return
     */
    public static List<Task> createTask(){
        List<Task> taskList = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            Task task = new Task(createService(i));
            taskList.add(task);
        }
        return taskList;
    }

    public static void start(ExecutorContext context){
        try {
            List<Task> task = createTask();
            for(int i = 0 ; i < task.size(); i++){
                System.out.println("===  任务"+(i+1)+"的执行结果 : " +context.execute(task.get(i)));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
