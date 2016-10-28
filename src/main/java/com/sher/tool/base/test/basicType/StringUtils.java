package com.sher.tool.base.test.basicType;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cloudsher on 2016/6/1.
 *
 *
 */
public class StringUtils {

    public static void println(String str){
        System.out.println(str);
    }


    /**
     * test stram
     * @return
     * @throws IOException
     */
    public static String getResult() throws IOException {
        PrintWriter writer = new PrintWriter("f://bb.json");

        FileOutputStream outStream = new FileOutputStream("f://bb.json");
        outStream.write(122342342);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.writeTo(outStream);
        byte[] bytes = out.toByteArray();
        for(byte b : bytes){
            System.out.println(b);
        }
//        ByteArrayInputStream in = new ByteArrayInputStream();

        return new String(out.toByteArray(),"utf-8");
    }


    /**
     * test class name
     *
     * @return  com.sher.tool.base.test.basicType.StringUtils
     */
    public static String classNameTest(){
        return StringUtils.class.getName();
    }

    public static void main(String args[]) throws IOException {
//        SystemUtil.println(classNameTest());
        testCompareAndSet();
    }

    /**
     * test compareAndSet(long ext,long update)
     *
     *  ext内部原有值等于期望值，更新ext内部值
     */
    public static void testCompareAndSet(){
        long now = System.currentTimeMillis();
        AtomicLong ext = new AtomicLong(Long.MIN_VALUE);
        long nw = ext.get();
        if(ext.compareAndSet(nw, now)){
            System.out.println("now :"+now + ",new :"+ nw+",atomiclong :"+ ext.get());
        }
    }

}
