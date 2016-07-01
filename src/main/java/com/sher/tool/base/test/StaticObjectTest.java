package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/31.
 *
 *  ��̬�࣬�ڵ��þ�̬�ķ�����ʱ����Ĺ��췽����û�б�ִ��
 */
public final class StaticObjectTest {

    static {
        System.out.println("static domain");
    }



    public static void main(String args[]) throws ClassNotFoundException {
//        StaticObjectTest.run();  //��̬�������ã����췽����û��ִ��

        Class.forName("StaticObjectTest");  //JVM�����ִ࣬��static����
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
