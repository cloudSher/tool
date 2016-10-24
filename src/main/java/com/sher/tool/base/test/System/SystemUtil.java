package com.sher.tool.base.test.System;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SystemUtil {

    public static void println(Object obj){
        System.out.println(obj);
    }


    public static void getProperties(){
        println(System.getProperty("user.dir"));
    }


    public static void main(String args[]){
        getProperties();
    }


}
