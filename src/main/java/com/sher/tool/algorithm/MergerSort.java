package com.sher.tool.algorithm;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by cloudsher on 2016/6/30.
 */
public class MergerSort {

    public static void main(String args[]){
        int[] arr = {2,4,5,1,3,7};
        int q = arr.length/2;
        merger(arr,0,q,arr.length);
        out.println(Arrays.toString(arr));
    }

    public static void merger(int[] arr,int p,int q,int r){
        if(p < r && p>=0){
            int left = arr[p];
            int right = arr[q];
            int g = p;
            while(p < q && q < r)
               if(left < right){
                   arr[g++] = left;
                   left = arr[++p];
               }else{
                   arr[g++] = right;
                   right = arr[++q];
               }
        }
    }

    public static void println(List list){
        for(int i = 0 ; i< list.size(); i++){
            out.println(list.get(i));
        }
    }


}
