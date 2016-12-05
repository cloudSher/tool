package com.sher.tool.trace;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MapOpration {


    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        Thread.sleep(2000 * 10);
        MapOpration opration = new MapOpration();
        opration.test();
    }

    public Map<String,Object> map = new HashMap<>();

    public boolean test(){
        map.put("11","map11");
        map.put("22","map22");

        System.out.println("map put" + map.size());

        map.put("33","map33");
        map.put("44","map44");

        return true;
    }

}

