package com.sher.tool.base.test.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ArrayListTest {

    public static void main(String args[]){

    }

    /**
     * stream();
     */
    public static void stream(){
        List<String> list = new ArrayList();
        list.stream().forEach(System.out::println);

        list.stream().filter(e -> !e.isEmpty());


    }





}
