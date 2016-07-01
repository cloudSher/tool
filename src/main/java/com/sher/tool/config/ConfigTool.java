package com.sher.tool.config;

/**
 * �ṩ�򵥵ľ�̬������ȡ�����Ӧ�����ñ�������������Zookeeper,Database����ĳ���ļ�
 * @author wuzhih
 *
 */
public class ConfigTool {
    static final PropertiesStore propStore;
    static
    {
        propStore=new LocalPropertiesStore();
    }

    /**
     * ��ȡĳ����Դ����Ӧ�����ñ�������PropertiesHolder��,�������Դ��Ҫ��ָIce����ͨ��һ�������Ӧһ��PropertiesHolder
     * �����Ӧ��PropertiesHolder�����ڣ���װ��defaultphId��Ӧ��PropertiesHolder
     * @param phId
     * @param defaultphId
     * @return
     */
    public static PropertiesHolder getPropertiesHolder(String phId,String defaultphId)
    {
        return propStore.loadPropertiesHolder(phId, defaultphId);
    }
}
