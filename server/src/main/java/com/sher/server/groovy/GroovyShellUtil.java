package com.sher.server.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.*;

/**
 * Created by Administrator on 2016/11/17.
 */
public class GroovyShellUtil {


    private static GroovyShell shell;

    static {
        shell = new GroovyShell();
    }

    public static Object run(File file) throws IOException {
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(file);
        return script.run();
    }

    public static Object run(Reader reader){
        if(reader == null)
            throw new NullPointerException("inputStream must not be null");
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(reader);
        return script.run();
    }

    public static Object loadRun(String classPath,String name,Object arg) throws IllegalAccessException, InstantiationException {
        GroovyClassLoader loader = new GroovyClassLoader();
        Class aClass = loader.parseClass(classPath);
        GroovyObject instance = (GroovyObject) aClass.newInstance();
        Object o = instance.invokeMethod(name, arg);
        return o;
    }


    public static void main(String args[]){
//        run(transform(classLoad("com/sher/groovy/Test.class")));
    }

    public static InputStream classLoad(String classPath){
        InputStream stream = GroovyShellUtil.class.getClassLoader().getResourceAsStream(classPath);
        if(stream!= null){
            return stream;
        }
        return null;
    }

    public static Reader transform(InputStream in){
        return  new InputStreamReader(in);
    }

}
