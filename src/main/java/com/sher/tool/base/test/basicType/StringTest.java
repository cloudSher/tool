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
        System.out.println(hash());
        System.out.println(hash_("123123123"));
    }

    public static void strTest(){
        String  s = "aa"+"bb";
        String s1 = "aabb";
        String a = "aa";
        String b = "bb";
        String c = a + b;
        System.out.println(s == s1);        //true;
        System.out.println(c == s1);        //false;
    }


    public static int hash(){
        return "123123123".hashCode();
    }


    /***
     * Daniel J.Bernstein  hash()
     * @param str  eg:123123123
     * @return  249811310355036327
     */
    public static long hash_(String str){
        long hash = 5381;
        for(int i = 0 ; i < str.length(); i++){
            hash = ((hash << 5) + hash)+ str.charAt(i);
        }
        return hash;
    }
    @Test
    public void test(){
        String s = new String(new char[]{'1','4','5'});
        s.intern();
        System.out.println(s == "145");
        String s1 = new String("456");
        s1.intern();
        System.out.println(s1 == "456");
    }
}
