package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 */
public class Config {

    private static Config config;

    private volatile int num;

    public static Config instance(){
        if(config == null){
            config = new Config();
        }
        return config;
    }

    public synchronized Config init(int num){
        this.num = num;
        try {
            System.out.println("-- config num :" + num + "-- thread : "+ Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public synchronized void printNum(){
        System.out.println(" config num : " + num + ",-- thread :" + Thread.currentThread().getName());
    }
}
