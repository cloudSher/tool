package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/31.
 *
 *  ��������Main����
 */
public class BaseTestMain {

    public static StaticObjectTest staticObjectTest;

    public static void main(String args[]){
//        StaticObjectTest.run();
        try {
            Class.forName("com.sher.tool.base.test.StaticObjectTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
