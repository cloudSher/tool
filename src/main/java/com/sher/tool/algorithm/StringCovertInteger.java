package com.sher.tool.algorithm;

/**
 * Created by sher on 11/6/16.
 */
public class StringCovertInteger extends AbstractTemplate{


    @Override
    public void test() {
        System.out.println(solution("1120"));
    }

    public int solution(String s){
        char[] chars = s.toCharArray();
        int result = 0;
        for(int i = chars.length -1; i >= 0 ;i--){
            result += (chars[i] - '0') * Math.pow(10,(chars.length-i-1));
        }

        return result;
    }
}
