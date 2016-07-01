package com.sher.tool.config;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by cloudsher on 2016/6/30.
 */
public class PropertiesUtils {


    public static Map<String,Object> prop2Map(Properties prop){
        Map<String,Object> map = null;
        if(prop != null){
            map = new HashMap<>(prop.size());
            Enumeration<?> enumeration = prop.propertyNames();
            while (enumeration.hasMoreElements()){
                String element = (String) enumeration.nextElement();
                if(element != null ){
                    map.put(element,prop.getProperty(element));
                }

            }
        }

        return map;
    }


    public static void writeFile(String path,Properties prop){
        File file = new File(path);
        if(prop == null){
            throw new NullPointerException();
        }
        try {
            if(file.exists()){
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
                prop.store(writer,"write properties to file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Properties map2Prop(Map<String,String> map){
        if(map == null || map.size() == 0){
            return null;
        }
        Properties prop = new Properties();
        for(Map.Entry<String,String> entry : map.entrySet()){
            prop.put(entry.getKey(),entry.getValue());
        }
        return prop;

    }
}
