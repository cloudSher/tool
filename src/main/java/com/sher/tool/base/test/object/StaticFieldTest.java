package com.sher.tool.base.test.object;

import com.sher.tool.model.User;

/**
 * Created by Administrator on 2016/11/4.
 *
 * static field is global status
 */
public class StaticFieldTest {

    private static User user = new User();
    private User user1 = new User();

    public User getUser(){
        return user;
    }

    public User getUser1(){
        return user1;
    }

    public static void main(String args[]){
        StaticFieldTest test = new StaticFieldTest();
        StaticFieldTest test1 = new StaticFieldTest();
        System.out.println(test.getUser() == test1.getUser());
        System.out.println(test1.getUser1() == test.getUser1());
    }



}
