package com.sher.tool.algorithm;

import java.util.Arrays;

/**
 * Created by cloudsher on 11/14/2016.
 */
public class LongestPalindrome {

    public  static void main(String args[]){

    }

    public static String longest(String s){
        if(s == null) return null;
        if(s.length() == 1) return s;
        char[] chars = s.toCharArray();
        int[] buffer = new int[128];
        Arrays.fill(buffer,-1);
        int start=0,end=0,maxLen = 0,tm=0;
        String result = null;
        while(end < chars.length){
            int index = buffer[s.charAt(end)];
            if(index >= 0){
                int t = end - index;
                if(t == 1 || t ==2){
                    start = index + 1;
                }else if (start >= 0){
                    tm = end - start;
                }
            }
            if(maxLen < tm){
                maxLen = tm;
                result = s.substring(start -1-(end - start),end);
            }
            buffer[s.charAt(end)] = end;
            end++;
        }
        return result;
    }
}
