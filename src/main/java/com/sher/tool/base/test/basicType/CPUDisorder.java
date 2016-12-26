package com.sher.tool.base.test.basicType;

import java.util.stream.IntStream;

/**
 * Created by cloudsher on 12/11/2016.
 *
 *  cpu 加载变量的乱序测试， a = 1, a = x时会乱序执行，由于x需要从主存加载，cpu优先执行其他指令
 */
public class CPUDisorder {

    int a = 0;
    int b = 0;
    int x =0,y =0;

    public static void main(String args[]){
        IntStream.range(0,100).forEach(CPUDisorder::doTest);
    }

    public static void doTest(int i){
        CPUDisorder instance = new CPUDisorder();
        Thread t1 = new Thread(()->{
            instance.a = 1;
            instance.x = instance.b;
        });
        Thread t2 = new Thread(()->{
            instance.b = 1;
            instance.y = instance.a;
        });
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        System.out.println("run case :" + instance.x + "," + instance.y );
    }
}
