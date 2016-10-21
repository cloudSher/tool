package com.sher.tool.base.test.System;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;
import java.util.Properties;

/**
 * Created by Administrator on 2016/10/18.
 */
public class CharsetTest {

    public static void main(String args[]){
        System.out.println(charset());
        System.out.println(fileEncodin());
        properties();
        System.out.println(numberProcessor());
        System.out.println(processsorNum());
    }

    public static String charset(){
        String csn = AccessController.doPrivileged(
                new GetPropertyAction("file.encoding"));
        return csn;
    }

    public static String fileEncodin(){
        return System.getProperty("file.encoding");
    }

    public static void properties(){
        System.getenv().entrySet().forEach(System.out::println);
    }

    public static String numberProcessor(){
        return System.getProperty("NUMBER_OF_PROCESSORS");
    }

    /***
     * 获取内核数
     * @return
     */
    public static String processsorNum(){
        return String.valueOf(Runtime.getRuntime().availableProcessors());
    }




}

