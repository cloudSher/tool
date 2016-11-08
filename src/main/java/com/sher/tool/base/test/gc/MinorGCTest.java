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


    /***
     *  -verbose:gc -Xms20m -Xmx20m -Xmn10m
     *
     *  young:10m -->edg:8m from:1m to:1m
     *  old:10m
     *
     */
    private final static int _1M = 1024 * 1024;
    public static void younggc(){
        byte[] bf1 = new byte[_1M];
        byte[] bf2 = new byte[2*_1M];
        byte[] bf3 = new byte[2*_1M];       // 直接放入old区域
    }


    /**
     * minor gc
     *  full gc : to区域直接放到old区域
     */
    public static void younggc1(){
        byte[] bf1 = new byte[_1M];
        byte[] bf2 = new byte[2*_1M];
        byte[] bf3 = new byte[2*_1M];
        byte[] bf5 = new byte[2*_1M];
        byte[] bf4 = new byte[4*_1M];
    }


    public static void main(String args[]){
//        minorGC();
//        younggc();
        younggc1();
    }
}
