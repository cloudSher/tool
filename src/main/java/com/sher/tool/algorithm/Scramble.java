package com.sher.tool.algorithm;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Scramble {

    /***
     *  穷举
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble(String s1,String s2){
        if(s1.equals(s2)){
            return false;
        }
        int min = 2;
        int len = s1.length();
        int max = len - 2;
        char[] chars0 = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        for(int i = min - 1; i < max; i++){
            int ltime = 0;
            int rtime = 0;
            int tmp = i;
            int l = len - 1;
            for(int j = 0 ; j < chars0.length;j++){
                if(tmp >= 0 && chars0[j] == chars1[tmp--]){
                    ltime++;
                }
                if(j > i && chars0[j] == chars1[j] && l == len -1){
                    rtime++;
                }else{
                    if(j > i && chars0[j] == chars1[l--]){
                        rtime++;
                    }
                }
            }
            if(ltime == i+1 && rtime == len -i-1){
                return true;
            }
        }
        return false;
    }

    public static boolean scramble(String s1,String s2){
        char[] chars0 = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        //定位分割线
        int split = 0;
        for(int i = 0; i <chars1.length;i++){
            if(chars0[0] == chars1[i]){
                split = i;
            }
        }
        if(split == 0){
            return false;
        }
        int left = split + 1;
        int rlen = chars0.length - 1;
        int ltime = 0;
        int rtime = 0;
        for(int j = 0; j < chars0.length;j++){
            if(split >= 0 && chars0[j] == chars1[split--]){
                ltime++;
            }else if(chars0[j] == chars1[j] && rlen == chars0.length -1){
                rtime++;
            }else if(chars0[j] == chars1[rlen--]){
                rtime++;
            }
        }
        if(ltime == left && rtime ==(chars0.length - left)){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        long startTime = System.nanoTime();
        System.out.println(isScramble("great","ergta")+",time is "+ (System.nanoTime()-startTime));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long start = System.nanoTime();
        System.out.println(scramble("great","ergta")+",time is "+ (System.nanoTime()-start));
    }
}
