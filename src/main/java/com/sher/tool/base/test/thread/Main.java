package com.sher.tool.base.test.thread;

/**
 * Created by cloudsher on 2016/7/14.
 *
 *  ��������ʷ����еı�������
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
