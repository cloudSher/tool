package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/31.
 *
 *  在首次发生下列任意一种情况时，一个类或接口类型T将被立即初始化：

 T是一个类，而且一个T类型的实例被创建；
 T是一个类，且T中声明的一个静态方法被调用；
 T中声明的一个静态字段被赋值；
 T中声明的一个静态字段被使用，而且这个字段不是一个常量字段；
 T是一个顶级类（top level class，见java语言规范的§7.6），而且一个断言语句嵌套在T内部被执行。
 */
public final class StaticObjectTest {

    private static Object obj = new Object();
    private int data = 30;
    private static int a = 10;

    {
        a = 50;
        System.out.println("data is " + data);
        System.out.println("static a : " + a);
        System.out.println("object code run");
    }


    static {
        System.out.println("static domain "+ a + ",obj"+obj);
    }




    public static void main(String args[]) throws ClassNotFoundException {
//        StaticObjectTest.run();         //静态方法执行的时候，是不是初始化类
//        StaticObjectTest.a = 20;
        new StaticObjectTest();
//        Class.forName("StaticObjectTest");
    }


    StaticObjectTest(){
        System.out.println("object control init:" + data + ",static a :" + a);
    }

    /**
     * static method
     */
    public static void run(){
        System.out.println("run is called .....");
    }

}
