package com.sher.tool.base.test.basicType;

import static java.lang.System.out;

/**
 * Created by sher on 10/24/16.
 */
public class ByteDemo {


    /**
     *  返回0〜255
     * @param data
     * @return
     */
    public static int getUnsignedByte(byte data){
        return data & 0xFF;
    }


    public static void main(String args[]){
        byte b = (byte)128;
        out.println(b);
        out.println(getUnsignedByte(b));
    }
}
