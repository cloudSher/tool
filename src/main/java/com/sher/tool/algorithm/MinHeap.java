package com.sher.tool.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MinHeap {

    public static void minHeap(){
        int[] arr = new int[]{70,6,30,55,5,9,2,1};
//        arr = new int[]{9,12,17,30,50,20,60,65,4,49};
        makeMinHeap(arr,arr.length);
        System.out.println(Arrays.toString(arr));
        minHeapSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }


    /***
     * 构造堆
     * @param arr
     * @param n
     */
    public static void makeMinHeap(int[] arr,int n){
        for(int i = n / 2 - 1 ; i >= 0; i--){
            minHeapFixdown(arr,i,n);
        }
    }

    /***
     * 删除节点操作，节点下沉
     * @param arr
     * @param i
     * @param n
     */
    public static void minHeapFixdown(int[] arr,int i,int n){
        // 当前节点i 与子节点
        int j = 2 * i + 1;
        int temp = arr[i];
        while(j < n){
            // 左右节点中最小的
            if(j+1 < n && arr[j] > arr[j+1]){
               j++;
            }

            if(arr[j] >= temp)
                break;
            arr[i] = arr[j];
            i = j;
            j = 2 * i +1;
        }
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void minHeapSort(int[] arr,int n){
        for (int i = n - 1; i >= 1; i--)
        {
            swap(arr,i, 0);
            minHeapFixdown(arr, 0, i);
        }
    }

    /**
     *   每次排序，把索引0的值跟最后节点的值交换，所以呈现倒序的效果
     *
     * [1, 5, 2, 6, 70, 9, 30, 55]
     [2, 5, 9, 6, 70, 55, 30, 1]
     [5, 6, 9, 30, 70, 55, 2, 1]
     [6, 30, 9, 55, 70, 5, 2, 1]
     [9, 30, 70, 55, 6, 5, 2, 1]
     [30, 55, 70, 9, 6, 5, 2, 1]
     [55, 70, 30, 9, 6, 5, 2, 1]
     [70, 55, 30, 9, 6, 5, 2, 1]
     [70, 55, 30, 9, 6, 5, 2, 1]
     * @param args
     */

    public static void main(String args[]){
        minHeap();
//        testSwap();
    }

    public static void testSwap(){
        int[] arr = {1,4};
        swap(arr,0,1);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 插入一个i节点，
     * @param arr
     * @param i
     */
    public static void insert(int[] arr,int i){
        int j,temp;
        temp = arr[i];
        j = (i - 1) /2;     //parent node

        while(j >= 0 && i!= 0){
            if(arr[j] > temp){
                arr[i] = arr[j];
                i = j;
                j = (i - 1)/2;
            }
        }
        arr[i] = temp;
    }

}
