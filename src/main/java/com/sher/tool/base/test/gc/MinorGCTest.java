package com.sher.tool.base.test.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 *  reference http://www.cnblogs.com/yang-hao/p/5939487.html
 *          http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html
 *          http://www.importnew.com/13954.html
 *          http://www.cnblogs.com/ityouknow/p/5614961.html
 *  gc test
 */
public class MinorGCTest {

    /***
     *  -Xms10m -Xmx10m -verbose:gc -server
     */
    public static void minorGC(){
//        Object[] buffer = new Object[10 * 1000 * 1000];     //java heap space out of memory exception
        int num = 0;
        Object obj;
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                Thread thread = Thread.currentThread();
                while(thread.isInterrupted()){
                    thread.interrupt();
                }
            }
        });
        List list = new ArrayList<>();
        while(true){
            obj = new Object();
            list.add(obj);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(++num);
        }
    }

    public static void yonggc(){

    }




    public static void main(String args[]){
        minorGC();
    }
}
