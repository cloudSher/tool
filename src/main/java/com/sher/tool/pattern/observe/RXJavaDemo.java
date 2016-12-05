package com.sher.tool.pattern.observe;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/30.
 */
public class RXJavaDemo {

    public static void main(String args[]){
        observable();
    }

    public static void observable(){
        Observable.just("hello world")
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("action arg :" + s);
                }
            });

        Observable.just("a","b","c").map(s -> s.toUpperCase())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
    }


}
