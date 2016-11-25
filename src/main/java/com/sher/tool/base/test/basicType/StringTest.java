package com.sher.tool.base.test.basicType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/7.
 */
public class StringTest {



    public void test(){
        Assert.assertEquals(new String(new char[]{'1','4','7'}).intern() == "147",true);
    }


    public static void main(String args[]){
        String  s = "aa"+"bb";
        String s1 = "aabb";
        String a = "aa";
        String b = "bb";
        String c = a + b;
        System.out.println(s == s1);        //true;
        System.out.println(c == s1);        //false;
    }

}
