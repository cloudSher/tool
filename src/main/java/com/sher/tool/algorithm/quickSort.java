package com.sher.tool.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class QuickSort {

    public int postition(int[] arr,int start,int end){
        int position = arr[start];
        while(start < end){
            // 从右边开始数
            while(arr[end] > position && start < end){
                end--;
            }
            arr[start] = arr[end] ;
            while(arr[start] < position && start < end){
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = position;
        return start;
    }


    public static void test(){
        int arr[] = new int[]{4,2,5,7,3,8,1};
        QuickSort s = new QuickSort();
//        s.sort(arr,0,6);
        quick_sort(arr,0,6);
        println(arr);
    }

    public static void println(int[] arr){
        for(int a = 0; a < arr.length; a++){
            System.out.println(arr[a]);
        }
        List<String> list = new ArrayList<>(arr.length);
        for(int i = 0 ;i <arr.length; i++){
            list.add(String.valueOf(arr[i]));
        }
        list.stream().forEach(System.out::print);

    }

    public static void main(String args[]){
        test();
    }

    public void sort(int[] arr,int start,int end){
        if(start >= end){
            return ;
        }
        int pos = postition(arr,start,end);
        sort(arr,0,pos);
        sort(arr,pos+1,end);
    }

    public static void quick_sort(int[] arr,int l,int r){
        if(l >= r){
            return ;
        }
        int x = arr[l];int i = l; int j = r;
        while(i < j){
            while(i < j && arr[j] > x){
                j--;
            }
            arr[i] = arr[j];
            while(i < j && arr[i] < x ){
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = x;
        quick_sort(arr,l,i-1);
        quick_sort(arr,i+1,r);
    }


}
