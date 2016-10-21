package com.sher.tool.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Administrator on 2016/10/20.
 */
public class CollectionUtil {


    public static void println(Map map){
        map.forEach((e,v)-> System.out.println(e+","+v));
    }

    public static void println(Collection coll){
        Optional<Collection> opt = Optional.of(coll);
        if(opt.isPresent()){
            Collection collection = opt.get();
            collection.forEach(System.out::println);
        }
    }
}
