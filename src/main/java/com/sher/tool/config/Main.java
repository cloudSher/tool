package com.sher.tool.config;

/**
 * Created by cloudsher on 2016/6/30.
 */
public class Main {

    public static void main(String args[]){
        String phId = "config";
        String defaultId = "local";
        String propName = "db.ice.ip";
        PropertiesHolder holder = ConfigTool.getPropertiesHolder(phId, defaultId);
        String propValue = holder.getPropValue(propName, (pName, oldValue, newValue) -> {
            System.out.println("propName:" + propName + ",oldValue:" + oldValue + ", newValue:"+newValue);
        });
        holder.setPropValue("db.ice.ip","192.168.1.101");

    }
}
