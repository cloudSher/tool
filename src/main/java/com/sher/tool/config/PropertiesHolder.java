package com.sher.tool.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 保持配置变量，配置变量可能来自Zookeeper,Database或者某个文件
 *
 * @author wuzhih
 *
 */
public class PropertiesHolder {
    private Map<String, String> propMap=new HashMap<String, String>();
    private Map<String, PropUpdatedHandler> propUpdatedHandlerMap = new HashMap<String, PropUpdatedHandler>();


    public void setPropValue(String propName,String propValue){
        synchronized (propMap){
            if (propUpdatedHandlerMap.containsKey(propName)){
                PropUpdatedHandler handler = propUpdatedHandlerMap.get(propName);
                String oldValue = propMap.get(propName);
                propMap.put(propName,propValue);
                handler.valueChanged(propName,oldValue,propValue);
            }else{
                propMap.put(propName,propValue);
            }
        }
    }

    public String getPropValue(String propName) {
        return propMap.get(propName);

    }
    /**
     * 对于运行期变化的配置变量，传入回调接口，当变量发生变化后，被及时通知修改赋值
     * @param propName
     * @param updatedHandler
     * @return
     */
    public String getPropValue(String propName, PropUpdatedHandler updatedHandler) {
        if (updatedHandler != null) {
            synchronized (propUpdatedHandlerMap) {
                propUpdatedHandlerMap.put(propName, updatedHandler);
            }
        }

        return getPropValue(propName);
    }

    public Map<String,String> getPropMap(){
        return this.propMap;
    }
}
