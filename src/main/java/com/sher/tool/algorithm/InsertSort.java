package com.sher.tool.algorithm;

import java.util.Arrays;

/**
 * Created by cloudsher on 10/30/2016.
 */
public class InsertSort {

    public static void main(String args[]){
        int arr[] = new int[]{5,2,1,4,7,6,9,3};
        insertSort(arr);
       println(arr);
    }

    public static int[] insertSort(int arr[]){
        for(int i = 1; i < arr.length; i++){
            int j,temp = arr[i];
            for(j = i - 1; j >= 0 && temp < arr[j];j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
        return arr;
    }

    public static void println(int[] arr){
        for(int i : arr){
            System.out.println(i);
        }
    }
}
