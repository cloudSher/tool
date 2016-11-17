package com.sher.tool.pattern.observe;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.Observable;

/**
 * Created by Administrator on 2016/11/17.
 */
public class ObservableDemo extends Observable implements SignalHandler {


    @Override
    public void handle(Signal signal) {
        System.out.println("signal action ...");
        setChanged();
        notifyObservers(signal);
    }

    public void handle(String sign){
        try{
            Signal.handle(new Signal(sign),this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void main(String args[]) throws InterruptedException {
        ObservableDemo d = new ObservableDemo();
        d.addObserver(new ObserverDemo());
        d.handle("TERM");
//        d.handle("STOP");
        Thread.sleep(90000);
    }
}
