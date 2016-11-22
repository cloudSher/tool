package com.sher.tool.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Demo {

    public static void event(){
        EventBus bus = new EventBus();
        bus.register(new Object(){

            @Subscribe
            public void listener(Integer count){
                System.out.println("event action : " + count);
            }
        });

        bus.post(2);
    }

    public static void main(String args[]){
        event();
    }
}
