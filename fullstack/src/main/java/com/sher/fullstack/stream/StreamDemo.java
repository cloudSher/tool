package com.sher.fullstack.stream;

import java.util.stream.Stream;

/**
 * Created by cloudsher on 9/4/2016.
 */
public class StreamDemo {

    public static void main(String args[]){
        collection();
    }

    public static void collection(){
        Stream.of("a","b","c","d").filter(s -> {
            System.out.println("filter is "+s);
            return true;
        }).forEach(System.out::println);
    }


}
