package com.sher.tool.base.test.lock;

/**
 * Created by Administrator on 2016/11/8.
 */
public class SynchronizedTest {


    /**
     * wait
     */
    class WaitObject{
        private Integer mutex;
        private int state;
        WaitObject(){
            mutex = new Integer(1);
            state = 1;
        }

        public void waitTest(){
            if(state == 1){
                try {
                    System.out.println("mutex wait...");
                    mutex.wait();               //抛异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("do action ...");
            }

        }

    }

    public static void main(String args[]){
        SynchronizedTest.WaitObject obj = new SynchronizedTest().new WaitObject();
        obj.waitTest();

    }


}
