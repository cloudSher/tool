package com.sher.tool.base.test.System.model;

/**
 * Created by cloudsher on 2016/7/14.
 */
public class User {

    private String name;
    private int age;
    private boolean isCreate;

//    public User(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isCreate=" + isCreate +
                '}';
    }
}
