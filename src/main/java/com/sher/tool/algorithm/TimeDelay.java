package com.sher.tool.algorithm;

import java.util.concurrent.TimeUnit;

/**
 * Created by a on 2017/3/21.
 *
 * 时间衰减模型
 */
public class TimeDelay {

    private static int threshold = 10;


    public static void main(String args[]){
        delay(0,0,0);
    }


    /**
     * 曝光数，
     *
     * 算法  log(c/sqrt(c-len + 1))
     */
    public static void delay(int export,int call,int click){
        float score;
        int max = 200;
        int req = 20;
        float exportScore,callScore,timeScore;
        float exportFactor,callFactor,timefactor;

        //TODO: System.currentTimeMillis  or Calendar.getTimeInMillis diff ?
        long curr = System.currentTimeMillis();
        for(int i = 0 ; i < req; i++){
            long c = System.currentTimeMillis() - curr;  //现在时间 - 创建时间
            exportScore = decay(export,max);
            callScore = decay(call,max);

            timeScore = timeLine3(c,86400 * 1000);
            System.out.println(timeScore);

            score =  0.2f * exportScore + 0.2f * callScore + 0.6f * timeScore;
//            System.out.println(0.6f * timeScore);
//            System.out.println("反应时间：" + c + " , 曝光数分数值 : " + exportScore + " , 拨打电话数分数值：" + callScore + ",时间衰减值："+ timeScore + ",总分数值 :" + score);
            try {
                TimeUnit.SECONDS.sleep( 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static float decay(int value,int max){
        if(value < max){
            return (float) Math.log(value);
        }else{
            return (float) Math.log(value/Math.sqrt(value - max + 1));
        }
    }


    public static float timeLineDecay(float time,float cycle){
        return (float) Math.pow(0.5,time/cycle);
    }

    public static float timeLine2(float time, float cycle){
        return (float)Math.log(86400 * 1000 / (time + 1));
    }

    public static float timeLine3(float time,float cycle){
        return (float) Math.log(86400000/Math.sqrt(time+1));
    }


}
