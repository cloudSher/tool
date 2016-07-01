package com.sher.tool.config;

import java.util.HashMap;
import java.util.Map;

/**
 * �������ñ��������ñ�����������Zookeeper,Database����ĳ���ļ�
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
     * ���������ڱ仯�����ñ���������ص��ӿڣ������������仯�󣬱���ʱ֪ͨ�޸ĸ�ֵ
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
