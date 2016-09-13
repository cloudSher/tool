package com.sher.tool.base.test.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class PubSubDemo {

    private List<Object> list = new ArrayList<>();

    class Product{

        public void post(){
            synchronized (PubSubDemo.this){
                if(list.size() > 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add("added");
                notify();
                System.out.println("product is runing");
            }
        }
    }

    class Consumer{

        public void consume(){
            synchronized (PubSubDemo.this){
                if(list.size() == 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (list.size()>0){
                    list.get(0);
                    list.clear();
                    notify();
                    System.out.println("consumer is comsuming!");
                }
            }
        }
    }

    public static void main(String args[]){



    }
}
