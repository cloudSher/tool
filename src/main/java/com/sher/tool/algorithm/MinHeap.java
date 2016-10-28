package com.sher.tool.algorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MinHeap {

    public static void minHeap(){
        int[] arr = new int[]{2,6,30,55,5,9,2,1};
//        arr = new int[]{9,12,17,30,50,20,60,65,4,49};
        makeMinHeap(arr,arr.length-1);
        minHeapSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }


    public static void makeMinHeap(int[] arr,int n){
        for(int i = n / 2 -1 ; i >= 0; i--){
            minHeapFixdown(arr,i,n);
        }
    }

    public static void minHeapFixdown(int[] arr,int i,int n){
        // 当前节点i 与子节点
        int j = 2 * i +1;
        int temp = arr[i];
        while(j<n){
            // 左右节点中最小的
            if(arr[j] > arr[j+1] && j+1 < n){
               j++;
            }

            if(arr[j]>=temp)
                break;
            arr[i] = arr[j];
            i = j;
            j = 2 * i +1;
        }
        arr[i] = temp;
    }

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    public static void minHeapSort(int[] arr,int n){
        for (int i = n - 1; i >= 1; i--)
        {
            swap(arr[i], arr[0]);
            minHeapFixdown(arr, 0, i);
        }
    }

    public static void main(String args[]){
        minHeap();
    }


}
