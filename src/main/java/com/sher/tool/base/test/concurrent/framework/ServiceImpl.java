package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 * 具体服务类
 */
public class ServiceImpl implements Service {

    private String name;
    private volatile int num;

    ServiceImpl(int num){
        this.num = num;
    }

    @Override
    public Object doRun() {
        try {
            Thread.sleep(100);
            System.out.println(" service num" + num + "-- thread:"+Thread.currentThread().getName());
            Config.instance().init(num).printNum();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
