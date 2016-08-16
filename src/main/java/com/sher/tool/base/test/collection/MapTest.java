package com.sher.tool.base.test.collection;

import com.sher.tool.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/16.
 *  测试迭代器 hasNext 是否判空操作 本质上，hasNext 只是判断当前游标是否等于数组长度
 *
 */
public class MapTest {


     public static void main(String args[]){
//         Map<String,String> map = new HashMap<>();
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

}
