package com.sher.tool.base.test.monitor;

import java.lang.instrument.Instrumentation;

/**
 * Created by cloudsher on 2016/6/17.
 */
public class Monitor {

    private static  Instrumentation inst ;

    public static void premain(String agentArgs, Instrumentation instP){
        inst = instP;
    }

    public static long sizeOf(Object obj){
        return inst.getObjectSize(obj);
    }

}
