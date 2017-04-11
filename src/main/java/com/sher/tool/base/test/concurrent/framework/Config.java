package com.sher.tool.base.test.concurrent.framework;

/**
 * Created by wei.zhao on 2017/3/25.
 *
 *  同一个配置类
 */
public class Config {

    private static Config config;

    private  int num;

    // 如果是单例类，会有并发问题
    public static Config instance(){
        if(config == null){
            config = new Config();
        }
        return config;
    }

    //每次访问都创建一个对象
    public static Config newInstance(){
        Config config = instance();
        System.out.println(" Config object: "+config + " -- thread: "+Thread.currentThread().getName());
        return config;
    }

    /**
     * 加synchronized 正常运行
     *
     *
     * @param num
     * @return
     */
    public  Config init(int num){
        this.num = num + 1;
        try {
            System.out.println(" Config.init: -- config num: " + this.num + " -- thread: "+ Thread.currentThread().getName() + " -- Instance: " + this);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public  void printNum(){
        System.out.println(" Config.printNum: -- config num : " + num + " -- thread :" + Thread.currentThread().getName() + " -- instance: " + this);
    }
}
