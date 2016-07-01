package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/31.
 *
 *  静态类，在调用静态的方法的时候，类的构造方法有没有被执行
 */
public final class StaticObjectTest {

    static {
        System.out.println("static domain");
    }



    public static void main(String args[]) throws ClassNotFoundException {
//        StaticObjectTest.run();  //静态方法调用，构造方法并没有执行

        Class.forName("StaticObjectTest");  //JVM加载类，执行static方法
    }


    StaticObjectTest(){
        System.out.println("static object init");
    }

    /**
     * static method
     */
    public static void run(){
        System.out.println("run is called .....");
    }

}
