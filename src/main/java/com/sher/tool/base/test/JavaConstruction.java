package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/17.
 *
 * ��
 */
public class JavaConstruction {

    /**
     * 可变参数测试
     */
    public static void test(int a){
        System.out.println(a);
    }

    public static void test(Integer ...obj){
        System.out.println(obj);
    }

//    public static void test(Object[] objs){
//
//    }

    public static void main(String args[]){
        test(5);
        test(3,4);
        test(new Integer[]{3,4});
        test(null);
        System.out.println(Integer.valueOf(100) == 100);
    }
}
