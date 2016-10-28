package com.sher.tool.algorithm;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by cloudsher on 2016/6/30.
 */
public class MergerSort {

    public static void main(String args[]){
        int[] arr = {2,4,5,1,3,7};
        mergeSort(arr,0,arr.length-1);
        out.println(Arrays.toString(arr));
    }

    public static void merger(int[] arr,int p,int q,int r){
        int[] temp = new int[arr.length];
        int k = 0;
        int mid = q+1;
        int left = p;
        while(p <= q & mid <= r){
            if(arr[p] <= arr[mid]){
                temp[k++] = arr[p++];
            }else{
                temp[k++] = arr[mid++];
            }
        }
        while(p <= q){
            temp[k++] = arr[p++];
        }
        while(mid <= r){
            temp[k++] = arr[mid++];
        }
        for(int i = 0 ; i < k; i++){
            arr[left + i] = temp[i];
        }
    }

    public static void mergeSort(int[] arr,int left,int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr,left,mid);        //左边有序
            mergeSort(arr,mid+1,right);     //右边有序
            merger(arr,left,mid,right);     //合并左右数组
        }
    }

    public static void println(List list){
        for(int i = 0 ; i< list.size(); i++){
            out.println(list.get(i));
        }
    }


}
