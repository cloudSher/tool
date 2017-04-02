package com.sher.tool.base.test.basicType;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by a on 2017/3/24.
 */
public class ComparatorTest {

    static class User{
        User(int age){
            this.age = age;
        }
        int age;
    }

    public void test(){
        List<User> user = Lists.newArrayList(new User(10),new User(20));
        user.sort((h1,h2)->{
            return h1.age < h2.age ? 1 : (h1.age==h2.age?0:-1);
        });
        user.forEach(user1 -> System.out.println(user1.age));
    }

    public static void main(String args[]){
        ComparatorTest test = new ComparatorTest();
        test.test();
    }

}
