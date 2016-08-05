package com.sher.tool.base.test.System;

import com.sher.tool.base.test.String.StringUtils;
import com.sher.tool.base.test.System.model.User;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;
import static java.lang.System.setSecurityManager;

/**
 * Created by cloudsher on 2016/6/1.
 *
 * System properties
 */
public class SystemPropertiesDemo {


    public static void main(String argsp[]){
//        securityManager();
//        crateObject();
//        listTest();
        SystemPropertiesDemo demo = new SystemPropertiesDemo();
        demo.reflectObject();
    }



    //class path
    public static void testPath(){
        String paths = System.getProperty("java.class.path").toString();
        StringUtils.println(System.getProperty("java.io.tmpdir"));

        String[] path = paths.split(";");
        FileSystem fileSystem = FileSystems.getDefault();
        Arrays.asList(path).stream()
                .map(p -> {
                    try {
                        return fileSystem.getPath(p).toUri().toURL().toString();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .forEach(System.out::println);
    }

    public static void securityManager(){
        out.println(System.getSecurityManager());
        if(System.getSecurityManager() == null){
            SecurityManager sc = new SecurityManager();
            System.setSecurityManager(sc);
        }
    }


    /**
     * 测试多线程情况下，构造函数是否会出现并发问题
     */
    public  void crateObject(){
        List users = new ArrayList(10);
        for(int i = 0 ; i < 10; i++){
            int age = i;
            String name = "zh"+i;
            new Thread(()->{
//                users.add(new User("当前线程:" + Thread.currentThread().getName()+ " User 的名称" + name,age));
            }).start();
        }
//        List list = Arrays.asList(new User("11",11),new User("22",22));
//        list.forEach(e -> System.out.println(e));
    }


    public  void reflectObject(){
        Class clazz = User.class;
        String name = "zw";
        int age = 19;
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Class[] classes = clazz.getClasses();
        out.println(classes.length);
        Arrays.asList(classes).forEach(out::println);
        Class superclass = clazz.getSuperclass();
        Class[] declaredClasses = clazz.getDeclaredClasses();
        out.println(declaredClasses.length);
        Type genericSuperclass = clazz.getGenericSuperclass();
        out.println(superclass);
        out.print(genericSuperclass.getTypeName());
        System.exit(1);
//        for(int i = 0 ; i< constructors.length;i++){
//            Constructor c = constructors[i];
//            Type[] types = c.getGenericParameterTypes();
//            Arrays.asList(types).forEach(out::println);
//            if(types.length > 1){
//                try {
//                    Object o =  c.newInstance(new SystemPropertiesDemo(),name,age);
//                    out.println(o);
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        Constructor cc = constructors[1];
        CountDownLatch latch = new CountDownLatch(10);
        SystemPropertiesDemo demo = new SystemPropertiesDemo();
        List<Object> list = new ArrayList<>(10);
        for(int i = 0 ; i < 10; i++){
            int flag = i;
            new Thread(()->{
            try {
                Object o = cc.newInstance(demo,name+"-"+flag,flag);
                latch.countDown();
                list.add(o);
                out.println(o);
                Thread.sleep(1000);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }).start();
        }

        list.forEach(out::println);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println(Home.type);
    }


    public  void listTest(){
        List<User> list = new ArrayList<>(10);
        for(int i = 0 ; i<10;i++){
//            User user = new User("11",i);
        }
        list.forEach(System.out::println);
    }

    /**
     * 测试对象，
     */
     class User extends SuperUser{
        private String name;
        private int age;
        private boolean isCraete;

       public User(){
        }

        public User(String name,int age){
            this.name = name;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.age = age;
            out.println("======当前对象的name："+name + ",=====age:" + age);
            if(name == null){
                isCraete = true;
            }else{
                isCraete = false;
            }


            Home.update(this);

        }



        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", isCraete=" + isCraete +
                    '}';
        }


    }


    static class Home{

        private static int type = 0;

        public static void update(User user){
            user.age++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.name = user.name + "-1111";
            type++;
        }
    }

    class User1 extends User{
        User1(){}
    }

    class SuperUser extends Supper implements Super {

    }

    abstract class Supper{

    }

    interface Super{

    }



}
