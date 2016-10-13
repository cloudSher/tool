package com.sher.tool.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/9/29.
 */
public class UIdGenerator {

    private static AtomicReference ref = new AtomicReference();
    private static AtomicInteger inf = new AtomicInteger();
    /***
     *
     * @return
     */
    public static String generatorId(){
        String timestamp = String.valueOf(System.currentTimeMillis());
        String increatNum = String.valueOf(inf.incrementAndGet());
        String uuid = uuid();

        return null;
    }


    public static String uuid(){
        return null;
    }

}
