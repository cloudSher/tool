package com.sher.tool.mysql;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/24.
 *
 *  //定时器
 */
public class ZTimerMain {

    private static Timer timer;

    static {
        timer = new Timer();
    }

    public static void main(String args[]){
        timer.schedule(timerTask("aa"),1000,2000);
    }

    public static TimerTask timerTask(String agr){
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println(agr);
            }
        };
    }


    public void multiExe(){

    }




}
