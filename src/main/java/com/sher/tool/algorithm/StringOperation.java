package com.sher.tool.algorithm;

/**
 * Created by Administrator on 2016/11/11.
 */
public class StringOperation {


    /**
     * 1,2,3,7,8,10  1-3,7-8,10
     */
    public static String getStr(String str){
        String[] arr1 = str.split(",");
        int start = 0,end = 0,len = arr1.length,n=1;
        int[] arr = strToInt(arr1);
        String result = arr[0]+"";
        start = n -1;
        while(n < len){
            if(arr[n - 1] + 1 == arr[n]){
                end++;
            }else{
                if(start<end && end>0){
                    result +=  "-" + arr[end];
                }
                start = end = n;
                result +=  "," + arr[n];
            }
            if(end == len -1){
                result+= "-"+arr[end];
            }
            n++;
        }

        return result;
    }

    public static String solution(String str){
        String[] strings = str.split(",");
        int[] arr = strToInt(strings);
        String result = strings[0];
        int n = 0 ,len,start =0;boolean flag = false;
        while(++n < (len = strings.length)){
            if(arr[n-1] + 1 == arr[n]){
                start++;
                flag = true;
                continue;
            }
            if(start>0 && flag){
                result += "-"+arr[start];
                flag = false;
            }
            start = n;
            result += "," + arr[n];
        }
        if(start == n-1 && flag){
            result +="-"+arr[start];
        }
        return result;
    }

    public static int[] strToInt(String[] arr){
        int[] ir = new int[arr.length];
        for(int i = 0 ; i < ir.length; i++){
            ir[i] = Integer.valueOf(arr[i]);
        }
        return ir;
    }


    public static void main(String args[]){
        String str = "1,2,3,6,8,9";
        str = "1,3,7,8,9";
        System.out.println(getStr(str));
        System.out.println(solution(str));
    }


}
