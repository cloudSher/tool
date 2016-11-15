package com.sher.tool.algorithm;

import java.util.*;

/**
 * Created by Administrator on 2016/11/1.
 */
public class LongSubString {

    public static int longSubString(String s1){
        char[] chars = s1.toCharArray();
        int length = chars.length,maxLen = 0;
        Map<Character,List> repeatPoint = new HashMap<>();
        for(int i = 0 ;  i < length- 1; i++){
            List<Integer> points = null;
            for(int j = i+1 ; j < length;j++){
                if(chars[i] == chars[j]){
                    if(points == null) {
                        points = new ArrayList<>();
                        points.add(i);
                    }
                    else{
                        points.add(j);
                    }
                    repeatPoint.put(chars[i],points);
                }
            }
        }
        //abcabcbb   a=>[0,3] b=>[1,4,6,7] c=>[2,5]
        List<Integer> val;
        char c;
        int start = 0,end = 0;
        Iterator<Map.Entry<Character, List>> iterator = repeatPoint.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Character,List> entry = iterator.next();
            while(iterator.hasNext()){
                Map.Entry<Character,List> it = iterator.next();
                c = it.getKey();
                val = it.getValue();
                start = val.get(0);
                end = val.get(val.size() -1);
            }
            List<Integer> currList = entry.getValue();
            int currStart = currList.get(0);
            int currEnd = currList.get(currList.size() -1);
            if(start != 0){
                //区间范围求值
                if(currEnd > start && currStart < start){
                    int left = currEnd;
//                    int mid =
                }else if(currEnd < start){

                }
            }else if(start == 0){
                maxLen = currEnd > (length - currStart -1) ? currEnd : (length-currStart -1);
            }


        }
        return maxLen;
    }


    public static int longSubStr(String s){
        if(s == null || s.trim().length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        char[] buffer = new char[len];
        buffer[0] = chars[0];
        int bufferSize = 1,index=0;
        int maxLen = 0;boolean isRepeat = false;
        for(int i = 1; i < len ; i++){
            int j;
            for(j = 0; j < bufferSize;j++){
                if(chars[i] == buffer[j]){
                    isRepeat = true;
                    break;
                }
            }
            if(!isRepeat){
                buffer[++index] = chars[i];
                bufferSize++;
            }else{
                int ml = bufferSize;
                if(ml > maxLen){
                    maxLen = ml;
                }
                //如果有重复的字符，重新申请有序空间
//                buffer = new char[len-maxLen];      //todo performance test
                int l = 0,k,p;
                for(k = 0,p = j; j < bufferSize-1 && k < bufferSize-1-j;k++){
                    if(p < bufferSize -1){
                        buffer[k] = buffer[++p];
                        l++;
                    }
                }
                buffer[k]= chars[i];
                index = k;
                bufferSize = l+1;
                isRepeat = false;
            }
        }
        if(bufferSize>0){
            maxLen = bufferSize > maxLen ? bufferSize:maxLen;
        }
        if(maxLen == 0){
            return len;
        }
        return maxLen;
    }

    public static String longestPalindrome(String s){
        String str=null;
        char[] chars = s.toCharArray();
        return str;
    }


    public static int bestSolution(String s){
        if(s == null){
            return 0;
        }else if(s.length() < 2){
            return s.length();
        }
        int[] arr = new int[128];
        Arrays.fill(arr,-1);
        int start = 0,end =0,max=0;
        while(end < s.length()){
            char c = s.charAt(end);
            int index = arr[c];
            if(index >= start){
                start = index + 1;
            }
            arr[c] = end;
            end++;
            max = Math.max(max,end - start);
        }
        return max;
    }

    public static void main(String args[]){
        System.out.println(longSubStr("abcabcbb"));
        System.out.println(longSubStr("abbbba"));
        System.out.println(longSubStr("bbbbbb"));
        System.out.println(longSubStr("pwcdwjacb"));     //5

        System.out.println(longSubStr("pcbqwerbaca"));
        System.out.println(bestSolution("pcbqwerbaca"));

    }
}
