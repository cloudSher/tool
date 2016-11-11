package com.sher.tool.base.test.lock;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Administrator on 2016/11/10.
 *
 *  test composite operation is unsafe
 */
public class ThreadUnSafe {

    private Vector vector = new Vector();

    public void get(){
        Iterator iterator = vector.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public void delete(int i){
        vector.remove(i);
    }


    /**
     * ConcurrentModificationException 来提示并发操作
     * @param args
     */
    public static void main(String args[]){
        ThreadUnSafe unSafe = new ThreadUnSafe();
        unSafe.vector.add("12");
        unSafe.vector.add("13");
        unSafe.vector.add("14");
        unSafe.vector.add("15");
        unSafe.vector.add("16");
        unSafe.vector.add("17");
        unSafe.vector.add("18");
        new Thread(()->{
            unSafe.get();
        }).start();

        new Thread(()->{
            unSafe.delete(2);
        }).start();
    }

}
