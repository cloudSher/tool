package com.sher.tool.base.test.basicType;

/**
 * Created by Administrator on 2016/10/25.
 */
public class ByteTest {


    public static void main(String args[]){
        test();
    }

    public static void test(){
        byte bt = (byte)138;
        System.out.println(bt);
        System.out.println(getUnsignByte(bt));
        System.out.println(getIntValue(bt));
    }

    public static int getUnsignByte(byte bt){
        return bt & 0xFF;
    }

    public static String getIntValue(byte bt){
        return Integer.toBinaryString(bt);          //11111111111111111111111110001010
    }


}
