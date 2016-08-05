package com.sher.tool.base.test.thread;

/**
 * Created by cloudsher on 2016/7/14.
 *
 *  匿名类访问方法中的变量问题
 */
public class Main {

    public static void main(String args[]){
        Main.Man man = new Main().new Man();
        final int num = 0;
        man.train(new Run() {
            @Override
            public void run() {
//                num++;
            }
        });

    }

    class Man{
        void train(Run run){
            run.run();
        }
    }

}
