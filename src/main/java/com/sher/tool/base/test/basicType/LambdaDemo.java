package com.sher.tool.base.test.basicType;

/**
 * Created by cloudsher on 12/15/2016.
 *
 *  测试返回值
 */
public class LambdaDemo {

    interface CallBack{
        void call(Info arg);
    }

    static class Inner{

        public void license(CallBack callBack){
            callBack.call(new Info());
        }
    }

    static class Info{
        private Info name = null;

        public Info getName() {
            return name;
        }
    }

    public static void main(String args[]){
        Inner inner = new Inner();
        final Info[] result = new Info[1];
        inner.license(c ->{
            result[0] = c.getName();
        });
        System.out.println(result[0]);
    }
}
