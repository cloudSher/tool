package com.sher.tool.pattern.reactor;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class Event<T> {

    private T t;


    public void setEvent(T t){
        this.t = t;
    }

    public T getEvent(){
        return this.t;
    }
}
