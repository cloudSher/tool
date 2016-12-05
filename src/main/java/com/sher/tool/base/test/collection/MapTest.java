package com.sher.tool.base.test.collection;

import com.sher.tool.model.User;

import java.util.*;

/**
 * Created by Administrator on 2016/8/16.
 *  测试迭代器 hasNext 是否判空操作 本质上，hasNext 只是判断当前游标是否等于数组长度
 *
 */
public class MapTest {


     public static void main(String args[]){
            testTreeMap();
     }


    public static void test(){
        //  Map<basicType,basicType> map = new HashMap<>();
        List<User> list = new ArrayList<>(1);
//         list.add(null);
//         map.put("11")
        for(User s : list){
            System.out.println("====="+ s.getName());
        }

        for(int i = 0 ; i < list.size();i++){
            System.out.println("==i=="+list.get(i).getName());
        }

    }

    public static void testTreeMap(){
        TreeMap map = new TreeMap();
        map.put(3L,new Node(3));
        map.put(9L,new Node(9));
        map.put(33L,new Node(33));
        map.put(56L,new Node(56));
        map.put(99L,new Node(99));

        SortedMap sortedMap = map.tailMap(34L);
        System.out.println("tail map is : " + sortedMap.firstKey());

        SortedMap sortedMap1 = map.headMap(50L);
        System.out.println(sortedMap1.firstKey());

    }




    static class Node{
        private long value;
        Node(long value){
            this.value = value;
        }
    }



}
