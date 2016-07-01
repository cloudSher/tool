package com.sher.tool.base.test.object;

/**
 * Created by cloudsher on 2016/6/28.
 */
public class Outer {


    private static String name;

    public void say(String nn){
        System.out.println(nn);
    }

    static class Inner{

        public void print(String str){
            name = str;
            System.out.println(name);
        }
    }

    public static void main(String args[]){
        Outer.Inner inner = new Outer.Inner();
        inner.print("zhansan");

        Outer outer = new Outer(){

        };
        outer.say("niming");
    }
}
