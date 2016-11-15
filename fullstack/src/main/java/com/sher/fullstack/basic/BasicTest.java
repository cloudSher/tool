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

        /**
         * -128二进制是：1111 1111 1111 1111 1111 1111 1000 0000
         *  负数在计算机用补码的形式表示
         *  真实-128： 1000 0000 0000 0000 0000 0000 1000 0000
         *   反码：    1111 1111 1111 1111 1111 1111 0111 1111
         *   反码+1    1111 1111 1111 1111 1111 1111 1000 0000
         */
        out.println(b1+"二进制是："+Integer.toBinaryString(b1));
    }
}
