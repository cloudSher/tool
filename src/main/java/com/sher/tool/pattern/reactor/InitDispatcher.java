package com.sher.tool.pattern.reactor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class InitDispatcher {

    private Map<String,EventHandler> handlerMap = new HashMap<>();

    private EventLoop loop;

    public void init(){
        if(loop == null){
            loop = new EventLoop();
        }
    }

    public void register_event(EventHandler handler,String type){
        if(handlerMap.size() == 0){
            if(handler != null)
                handlerMap.put(type,handler);
            throw new NullPointerException("handler is not null");
        }
    }

    public boolean remove_event(String type){
        if(handlerMap.containsKey(type)){
            handlerMap.remove(type);
        }
        return true;
    }

    public void start(){
        loop.select("");
    }



}
