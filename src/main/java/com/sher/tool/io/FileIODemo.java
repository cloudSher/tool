package com.sher.tool.io;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Created by cloudsher on 2016/6/16.
 */
public class FileIODemo implements FileIOInterface {

    public  OutputStream out(String path){
        OutputStream out = null;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }


    public  InputStream in(String path){
        InputStream in = null;
        try {
            if(path.contains("http")){
                in = new URL(path).openConnection().getInputStream();
            }
            else if(Paths.get(path) != null){
                in = new FileInputStream(path);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public  void handler(String inPath,String outPath){
        InputStream in = in(inPath);
        OutputStream out = out(outPath);
        try {
            if(in != null && out != null){
                byte[] buff = new byte[1024];
                int len;
                while((len = in.read(buff)) != -1){
                    out.write(buff,0,len);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]){
        FileIODemo ioDemo = new FileIODemo();
        FileIOInterface proxy = (FileIOInterface) proxy(ioDemo);
//        proxy.handler("f://crawl/329523109c7ac4b0ed916f5a82c99b91.jpg", "f://bb.jpg");

        proxy.handler("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=667412574,1544342783&fm=80","f://ee.jpg");
        FileIOInterface nio = new FileNIO();
        FileIOInterface proxyIO = (FileIOInterface) proxy(nio);
//        proxyIO.handler("f://crawl/329523109c7ac4b0ed916f5a82c99b91.jpg","f://cc.jpg");

        proxyIO.handler("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=667412574,1544342783&fm=80","f://dd.jpg");
    }




    public static Object proxy(Object obj){
        return Proxy.newProxyInstance(FileIODemo.class.getClassLoader(),FileIODemo.class.getInterfaces(),new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();
                method.invoke(obj,args[0],args[1]);
                System.out.println("time is : " + (System.currentTimeMillis() - start) + " ms");
                return obj;
            }
        });
    }
}
