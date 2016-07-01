package com.sher.tool.config;

/**
 * ����������ñ���������PropertiesHolder����
 *
 * @author wuzhih
 *
 */
public abstract class PropertiesStore {
    /**
     * ��ȡĳ����Դ����Ӧ�����ñ�������PropertiesHolder��,�������Դ��Ҫ��ָIce����
     * ͨ��һ�������Ӧһ��PropertiesHolder
     * �����Ӧ��PropertiesHolder�����ڣ���װ��defaultphId��Ӧ��PropertiesHolder������������ڣ��򷵻ؿյ�PropertiesHolder
     *
     * @param phId
     * @param defaultphId
     * @return
     */
    public abstract PropertiesHolder loadPropertiesHolder(String phId, String defaultphId);

    /**
     * �־û��������ñ���
     * @param ph
     */
    public abstract void updatePropertiesHolder(PropertiesHolder ph);
}