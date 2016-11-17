package com.sher.tool.algorithm;

import java.util.Arrays;

/**
 * Created by cloudsher on 11/14/2016.
 */
public class LongestPalindrome {

    public  static void main(String args[]){
//        System.out.println(longestStr("babacd"));
        System.out.println(longestStr("cbcbbbd"));
    }

    public static String longest(String s){
        if(s == null) return null;
        if(s.length() < 2) return s;
        int[] buffer = new int[128];
        Arrays.fill(buffer,-1);
        int start=-1,end=0,maxLen = 0;
        String result = null;
        while(end < s.length()){
            int index = buffer[s.charAt(end)];
            if(index >= 0){
                int t = end - index;
                if(t == 1){
                    if(start < 0)
                        start = index;
                }
                else if(t == 2){
                    start = index;
                }
                if (start >= 0 && index == start -1){      //如果相邻的2个指针相等，则start左移
                    start = start-1 < 0 ? 0 : start -1;
                }
               else if(index != start -1 && start>=0){          //如果相邻的2个不相等，直接获取
                    if(maxLen < end - start){
                        maxLen = end - start;
                        result = s.substring(start,end);
                    }
                    start = -1;
                }
            }
            if(index < 0 && start >=0){
                if(maxLen < end - start){
                    maxLen = end - start;
                    result = s.substring(start,end);
                }
                start = -1;
            }
            buffer[s.charAt(end)] = end;
            end++;
        }
        return result;
    }


    /**
     *
     * @param s
     * @return
     */
    public static String longestStr(String s){
        if(s.length() < 2) return s;
        int[] arr = new int[128];
        Arrays.fill(arr,-1);
        int start = -1,end = 0,maxLen = 0;
        String result = null;
        while(end < s.length()){
            int index = arr[s.charAt(end)];
            //出现重复字符
            if(index >= 0){
                int t = end - index;
                if(t == 1){         //连续字符
                    if(start <= 0){
                        start = index;
                    }
                    arr[s.charAt(end)] = end;
                    end++;
                    continue;
                }
                if(t == 2){     //对称字符
                    start = index;
                    arr[s.charAt(end)] = end;
                    end++;
                    continue;
                }

                if(start >= 0 && index == start -1){
                    start = start - 1 == 0? 0 : start -1 ;
                }else{
                    if(maxLen < end - start){
                        maxLen = end - start;
                        result = s.substring(start,end);
                    }
                    start = -1;
                }

            }
            if(index < 0 && start >= 0){
                int t;
                if(maxLen < (t = end - start)){
                    maxLen = t;
                    result = s.substring(start,end);
                }
                start = -1;
            }

            arr[s.charAt(end)] = end;
            end++;
        }

        return result;
    }

}
