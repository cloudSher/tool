package com.sher.tool.base.test.System;

import com.sher.tool.base.test.String.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Arrays;

/**
 * Created by cloudsher on 2016/6/1.
 *
 * System properties
 */
public class SystemPropertiesDemo {



    public static void main(String argsp[]){


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




}
