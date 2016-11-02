package com.sher.tool.base.test;

/**
 * Created by cloudsher on 2016/5/31.
 *
 *
 */
public class BaseTestMain {

    public static StaticObjectTest staticObjectTest;



    public static void main(String args[]){
//        StaticObjectTest.run();

        referenceTest();

    }

    /**
     * 静态类初始化时，what happen;
     */
    public static void classTest(){
        try {
            Class.forName("com.sher.tool.base.test.StaticObjectTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * java 值赋值
     */
    private static Internal last;
    public static void referenceTest(){
        final Internal internal = last;
        final Internal newInter1 = new Internal();
        last = newInter1;
        if(internal == null){
            System.out.println("last is null");
        }else{
            System.out.println("last is not null," + internal);
        }
        internal.next=newInter1;
    }

    static class Internal<E>{
        E item;
        Internal next;
        Internal(){

        }

        Internal(E e){
            this.item = e;
        }
    }


}
