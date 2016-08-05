package com.sher.tool.base.test.System;

import java.util.Timer;
import java.util.TimerTask;
import static java.lang.System.out;

/**
 * Created by cloudsher on 2016/7/14.
 */
public class Time_Test {

    private static int num1;

    public static void main(String args[]){
        Timer timer = new Timer();
        final Integer num = 1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                num1++;
                out.println("task is run");
            }
        },1000);

    }
}
