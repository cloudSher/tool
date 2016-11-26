package com.sher.tool.base.test.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by sher on 11/6/16.
 */
public class LinkedHashMapTest {

    @Test
    public void test(){
        LinkedHashMap map = new LinkedHashMap();
        map.put("str","123");
    }


    public static void main(String args[]){
        LinkedHashMap map = new LinkedHashMap();
        map.put("str","123");
        map.put("str","456");
        System.out.println(map.size());
        HashMap map1 = new HashMap();
        map1.put("123","13");
    }
}
