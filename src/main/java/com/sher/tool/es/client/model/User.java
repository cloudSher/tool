package com.sher.tool.es.client.model;

/**
 * Created by cloudsher on 2016/5/20.
 */
public class User {

    private String name;
    private String gender;
    private int age;
    private String interests;
    private String about;

    public User(String name, String gender, int age, String interests, String about) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.interests = interests;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
