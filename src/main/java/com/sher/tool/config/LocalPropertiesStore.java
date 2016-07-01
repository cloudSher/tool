package com.sher.tool.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.Properties;

/**
 * ���ر���properies�ļ���Ϊ���òֿ⣬����phIdΪproperties�ļ������֣���������׺��
 * �����ļ�������
 *  ��������ʱ��ĵ�ǰĿ¼
 *  classpath�����Դ�ļ�
 * @author wuzhih
 *
 */
public class LocalPropertiesStore extends  PropertiesStore {

    private String path;

    public void setPath(String path){
        this.path = path;
    }

    @Override
    public PropertiesHolder loadPropertiesHolder(String phId, String defaultphId) {
        PropertiesHolder holder = null;
        try {
            //���������ļ�
            String prop = phId + ".properties";
            String defPro = defaultphId + ".properties";
            InputStream stream = getInputStream(prop);

            if(stream == null){
                stream = getInputStream(defPro);
            }
            holder =  new PropertiesHolder();
            if( stream == null){
                return holder;
            }

            Properties properties = new Properties();
            properties.load(stream);

            load(properties,holder);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return holder;
    }

    @Override
    public void updatePropertiesHolder(PropertiesHolder ph) {
        if(ph != null){
            if(path == null){
                path = "/config.properties";
            }
            PropertiesUtils.writeFile(path, PropertiesUtils.map2Prop(ph.getPropMap()));
        }
    }


    public InputStream getInputStream(String prop){
        InputStream resourceAsStream = this.getClass().getResourceAsStream(prop);
        if(resourceAsStream == null){
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            resourceAsStream = loader.getResourceAsStream(prop);
        }
        return resourceAsStream;
    }


    public void load(Properties prop,PropertiesHolder holder){
        if(prop != null){
            Enumeration<?> propertyNames = prop.propertyNames();
            if(propertyNames.hasMoreElements()){
                String element = (String) propertyNames.nextElement();
                holder.setPropValue(element,prop.getProperty(element));
            }
        }
    }



}

