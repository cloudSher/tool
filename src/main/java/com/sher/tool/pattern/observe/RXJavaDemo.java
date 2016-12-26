package com.sher.tool.pattern.observe;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/30.
 *
 *   异步的方式实现的观察者模式
 */
public class RXJavaDemo {

    public static void main(String args[]){
        observable();
//        async();
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


    public static void async(){
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }





}
