package com.sher.tool.base.test.object;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ConstrcutorTest {

    static class Base{

        void print(){
            System.out.println("base print...");
        }

        Base(){
            System.out.println("base init before");
            print();
            System.out.println("base init after");
        }
    }

    static class SubClass extends Base{
        private int num = 1;

        SubClass(int r){
            System.out.println("sub class before num is "+ num);
            num = r;
            System.out.println("sub class num is "+ num);
        }

        void print(){
            System.out.println("sub class num is "+ num);
        }

    }

    public static void main(String args[]){
        new SubClass(3);
    }
}
