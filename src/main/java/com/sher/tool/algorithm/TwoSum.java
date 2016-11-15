package com.sher.tool.algorithm;

/**
 * Created by cloudsher on 10/20/2016.
 */
public class TwoSum {


    public static void main(String args[]){
        twoSum(new int[]{3,2,4},6);
    }

    public static int[] twoSum(int[] arr,int target){
        int [] result = new int[2];
        for(int i = 0 ; i < arr.length; i++){
            for(int j  = i + 1; j < arr.length; j++){
                int sum = arr[i] + arr[j];
                if (sum == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
