package com.sher.tool.pattern.reactor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class EventHandler implements Callable {

    private Map<String,Event> eventMap;

    public EventHandler(){
        if(eventMap == null){
            eventMap = new HashMap<>();
        }
    }

    /**
     * ���
     * @param type
     */
    public void handle_event(String type){
        if(eventMap.containsKey(type)){
            Event event = eventMap.get(type);
        }
    }

    public void get_event(String type){

    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
