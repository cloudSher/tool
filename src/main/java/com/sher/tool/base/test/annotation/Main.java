package com.sher.tool.base.test.annotation;

import com.sher.tool.model.User;

import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Main {

    public static void main(String args[]){
        Class<User> clazz = User.class;
        if(clazz.isAnnotationPresent(Serialize.class)){
            System.out.println("user class has a annotation is serialize");
            Annotation annotation = clazz.getAnnotation(Serialize.class);
            Serialize userSerialize = (Serialize) annotation;
            if(annotation != null){
                System.out.println("value :" + annotation.annotationType());
            }
            if(userSerialize != null){
                System.out.println("value :" + userSerialize.values());
            }
        }
    }
}
