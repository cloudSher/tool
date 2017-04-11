package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 * 具体服务类
 */
public class ServiceImpl implements Service {

    private String name;
    private int num;

    ServiceImpl(int num){
        this.num = num;
    }

    /**
     * 如果Config 是每次访问都创建对象，jvm会保证对象引用方法被线程栈中保存的对象引用，正确处理
     *
     * @return
     */
    @Override
    public Object doRun() {
        try {
            Thread.sleep(100);
            System.out.println(" Service: -- service num: " + num + " -- thread: "+Thread.currentThread().getName());
//            Config.newInstance().init(num).printNum();
            ConfigManager.exec(num);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
