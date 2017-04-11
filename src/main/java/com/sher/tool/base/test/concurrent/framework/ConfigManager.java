package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/4/7.
 */
public class ConfigManager {

    private static int num =0;

    public static void exec(int num){
        Config.newInstance().init(num).printNum();
//        System.out.println(" -- " +Thread.currentThread().getName() +" num: " + num++);
    }

}
