package com.sher.tool.es.client;

import com.sher.tool.es.client.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloudsher on 2016/5/20.
 */
public class GeneratDocument {


    public static void main(String args[]){

    }


    public static List<User> createDocument(int num){
        List<User> list = new ArrayList<>(num);
        User u;
        for(int i = 0 ; i< num; i++){
            u = new User("ÕÅÈý"+i,i%2==0?"Å®":"ÄÐ",10+i,"sports,music","I love to go rock climbing");
            list.add(u);
        }
        return list;
    }

}
