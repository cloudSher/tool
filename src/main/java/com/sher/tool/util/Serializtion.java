package com.sher.tool.util;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Serializtion {

    public static byte[] serialization(Object obj) {
        if(obj == null){
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static String unSerialzation(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, System.getProperty("file.encoding"));
    }

    public static void phySerialization(Object obj,String path){
        if(obj == null)
            return ;
        try {
            File file = toFile(path);
            FileOutputStream out = null;
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File toFile(String path){
        return Paths.get(path).toFile();
    }

    public static Object unPhySerialization(String path){
        File file = toFile(path);
        Object o = null;
        try {
            if(file.exists()){
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream in  = new ObjectInputStream(fin);
                o = in.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }


    public static void test(){
        Persistence persistence = new Persistence("zhangsan","20");
        Persistence p2 = new Persistence("lisi","30");
        String path = "f://persistence";
//        phySerialization(persistence,path);
        Object o = unPhySerialization(path);
        if(o instanceof Persistence){
           Persistence p =  (Persistence)o;
            System.out.println(p.getData()+","+p.getAge());
        }
    }

    public static void main(String args[]){
        test();
    }

    static class Persistence implements Serializable{

        private String data;
        private String age;

        Persistence(String data,String age){
            this.data = data;
            this.age = age;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
