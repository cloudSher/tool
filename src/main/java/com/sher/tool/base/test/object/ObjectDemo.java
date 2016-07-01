package com.sher.tool.base.test.object;

import com.sher.tool.base.test.monitor.Monitor;

import java.lang.instrument.Instrumentation;

/**
 * Created by cloudsher on 2016/6/16.
 */
public class ObjectDemo {

    static class A{
        int a;
    }

    public static void main(String arg[]){

        Integer integer = new Integer(1);
        System.out.println("Integer size : " + Monitor.sizeOf(integer));
        System.out.println("A size : " + Monitor.sizeOf(new A()));
        System.out.println("Object size : " + Monitor.sizeOf(new Object()));

    }

}
