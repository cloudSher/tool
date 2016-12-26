package com.sher.tool.base.test.basicType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public class ListTest {


    public static void main(String args[]){
        List<String> list = new ArrayList(){{
            add("111");
        }};

        System.out.println(list.get(0));
    }
}
