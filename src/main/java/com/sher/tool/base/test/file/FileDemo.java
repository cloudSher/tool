package com.sher.tool.base.test.file;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Created by Administrator on 2016/10/24.
 */
public class FileDemo {

    public static void main(String args[]) throws IOException {
//            listFile("F://sample");
//        list(new File("f://sample"));
//        filePath("F:\\alipay-sdk-JAVA-20160417220106\\com\\alipay\\api\\AlipayApiException.java");
//        generateJar("F:/sample");
//        generateSimpleJar("f:/test.jar");

//        filePath(".");

        System.out.println(pathString("tmp"));
    }

    public static void listFile(String path){
        if(path!=null){
            File file = new File(path);
            if(file.exists()){
                Arrays.asList(file.listFiles()).forEach(System.out::println);
            }
        }
    }

    public static void list(File file){
        System.out.println(file.list().length);
        Arrays.asList(file.list()).forEach(System.out::println);
    }

    /***
     * 获取文件全路径
     *
     * @param path "F:\alipay-sdk-JAVA-20160417220106\com\alipay\api\AlipayApiException.java"
     */
    public static void filePath(String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            System.out.println(file.getName());             //只返回文件名 AlipayApiException.java
            System.out.println(file.getAbsoluteFile());     //F:\alipay-sdk-JAVA-20160417220106\com\alipay\api\AlipayApiException.java
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getPath());
            System.out.println(file.getParent());
        }
    }


    public static void generateJar(String sourcePath) throws IOException {
        File source = new File(sourcePath);
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,"1.0");
        JarOutputStream jos = new JarOutputStream(new FileOutputStream(source+".jar"));
        writeClass(source,jos);
        jos.close();
    }

    public static void writeClass(File source,JarOutputStream jos){
        BufferedInputStream bin = null;
        if(source.isDirectory()){
            for(File file : source.listFiles()){
                writeClass(file,jos);
            }
            return;
        }

        String name = source.getPath().replace("\\","/").substring("F:/sample".length()+1);
        System.out.println(name);
        JarEntry entry = new JarEntry(name);
//        entry.setTime(source.lastModified());
        try {
            jos.putNextEntry(entry);
            bin = new BufferedInputStream(new FileInputStream(source));
            readAndWrite(bin,jos);
            jos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readAndWrite(BufferedInputStream bin,OutputStream out) throws IOException {
        int len;
        while((len = bin.read()) != -1){
            out.write(len);
        }
    }

    public static void generateSimpleJar(String path) throws IOException {
        File file = new File(path);
        JarOutputStream jos = new JarOutputStream(new FileOutputStream(path));
        String name = "net/xulingbo/zookeeper/locks/Locks.java";
        JarEntry entry = new JarEntry(name);
        jos.putNextEntry(entry);
        File target = new File("F:/sample/net/xulingbo/zookeeper/locks/Locks.java");
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(target));
        byte[] buffer = new byte[1024];
        int len;
        while((len = bin.read(buffer))!=-1){
            jos.write(buffer,0,len);
        }
        jos.closeEntry();
        jos.close();
    }


    public static String pathString(String str) throws IOException {
        System.out.println(FileDemo.class.getResource(""));             //file:/E:/Project/demo/tool/build/classes/main/com/sher/tool/base/test/file/
        System.out.println(FileDemo.class.getResource("/"));           //file:/E:/Project/demo/tool/build/classes/main/
        System.out.println(FileDemo.class.getClassLoader().getResource(""));            //file:/E:/Project/demo/tool/build/classes/main/
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));         //file:/E:/Project/demo/tool/build/classes/main/
        return Paths.get("").toFile().getAbsolutePath();
    }

}
