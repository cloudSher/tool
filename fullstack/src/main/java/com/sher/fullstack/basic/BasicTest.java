package com.sher.fullstack.basic;

import static java.lang.System.out;

/**
 * Created by Administrator on 2016/8/17.
 */
public class BasicTest {

    public static  void main(String args[]){
        byte_test();
    }

    /**
     * 测试byte类型值边界问题
     */
    public static void byte_test(){
        byte b = 127;                   // 0111 1111
        byte bb = (byte) (b << 2);      //由于byte基本类型的值范围是0-127, 01 1111 1100
        int bb_i = b << 2;
        out.println("byte :" + bb+"=== bb_i:"+bb_i);

        //byte类型值范围
        byte b1 = -128;
    }
}
