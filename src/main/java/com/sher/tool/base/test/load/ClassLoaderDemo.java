package com.sher.tool.base.test.load;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ClassLoaderDemo {

    private static AAA aaa;

    public static void main(String args[]) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class aClass = AAA.class;
        Object instance = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("clear", null);
        method.invoke(instance,null);
    }
}
