package com.sher.tool.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */
public class View {

    private static int value1;
    private static int value2;
    private static Map<Integer,Integer> map = new HashMap<>();


    public static void main(String args[]){
        int[] arr = {4,2,6,3,45,12,34,10,5,3,1,90,30,70};
        //排序之后的：1,2,3,3,4,5,6,10,12,30,34,45,70,90
        long start = System.nanoTime();
//        Arrays.sort(arr);
//        getResult(arr,8);
        force(arr,8);
        printlnEle();
        System.out.println("time is "+ (System.nanoTime()-start));
    }

    public static void test(){

    }

    public static void getResult(int[] arr,int value){
        invoke(arr,0,arr.length-1,value);
    }


    /***
     * 二分在解决求和问题，存在左边和右边之和的计算
     * @param arr
     * @param start
     * @param end
     * @param value
     */
    public static void invoke(int[] arr,int start,int end,int value){
        if (start >= end)
            return ;
        int mid = (start + end) / 2;
        int leftMax = arr[mid]+arr[start];
        if(leftMax > value){
            invoke(arr,start,mid,value);
        }
        int sum = arr[mid] + arr[mid +1];
        if(sum == value){
            map.put(arr[mid],arr[mid+1]);
        }
        invoke(arr,start,mid,value);
        invoke(arr,mid + 1,end,value);

    }

    public static void force(int[] arr,int value){
        for(int i = 0; i < arr.length - 1; i++){
            int val1 = arr[i];
            for(int j = i + 1 ; j < arr.length;j++){
                int val2 = arr[j];
                int sum = val1 + val2;
                if(sum == value){
                    map.put(val1,val2);
                }
            }

        }
    }


    public static void printlnEle(){
        map.forEach((e,b) ->{
            System.out.println(e+","+b);
        });
    }



}
