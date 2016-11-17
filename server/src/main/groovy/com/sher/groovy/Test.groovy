package com.sher.groovy

/**
 * Created by Administrator on 2016/11/17.
 */
class Test {

    static int add(int x,int y) {
        return x+y;
    }

    static main(args) {
        int z = add(123,321);
        println("x+y="+z);

    }
}