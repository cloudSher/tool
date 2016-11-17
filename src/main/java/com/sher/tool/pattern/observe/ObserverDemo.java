package com.sher.tool.pattern.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/11/17.
 *
 *  实现信号的观察者模式
 */
public class ObserverDemo implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("目标对象：" + o+",arg:"+arg);
    }


}
