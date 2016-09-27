package com.sher.tool.base.test.String;

import com.sher.tool.base.test.System.SystemUtil;

/**
 * Created by Administrator on 2016/9/27.
 */
public class IntegerTest {

    public static void main(String args[]){

        SystemUtil.println(integerMinValue());
        SystemUtil.println(compressId(100));
        SystemUtil.println(0xFFFFFFFFFFFFFFFFL);
    }


    public static int integerMinValue(){
        Integer n = Integer.MIN_VALUE;
        return n;
    }

    public static long compressId(long id){
        return id >>> integerMinValue() ;
    }


}
