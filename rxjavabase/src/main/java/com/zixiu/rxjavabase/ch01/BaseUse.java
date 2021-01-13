package com.zixiu.rxjavabase.ch01;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author: Snail
 * Time:  2021/1/12 11:19 AM
 * FileName:  BaseUse
 * 简介：RxJava基本使用
 * 文章链接：https://www.jianshu.com/p/a406b94f3188
 */
public class BaseUse {

    public static void main(String[] args) {
        /**
         * <p>步骤一：创建Observable的几种方式</p>
         * 方式一：Observable.create()
         */
        //1、创建被观察者Observable对象
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            //create()是RxJava最基本的创造时间的方法
            //此处传入OnSubscribe对象
            //当Observable被订阅时，OnSubscribe的call()方法会被自动调用，即事件序列就会按照设定依次触发
            //即观察者会依次调用对应事件的复写方法从而响应事件
            //从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) {
                //通过ObservableEmitter对象产生事件通知观察者
                //ObservableEmmit:事件发射器，定义需要发送的事件 & 向观察者发送事件
                emitter.onNext(1 + "");
                emitter.onNext(2 + "");
                emitter.onNext(3 + "");
                emitter.onComplete();
            }
        });

        /**
         * 方式二：Observable.just(String ...)
         * 将会依次调用onNext("A");onNext("B");onNext("C");onCompleted();
         */
        Observable<String> observable2 = Observable.just("A", "B", "C");
        /**
         * 方式三：
         */
        String[] words = {"D", "E", "F"};
        Observable<String> observable3 = Observable.fromArray(words);

        /**
         * <p>步骤二：创建观察者Observer</p>
         * 方式一：采用Observer接口的方式
         */
        Observer<String> observer1 = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("observer1 --> onSubscribe()");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("observer1 --> onNext( s = " + s + ")");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("observer1 --> onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("observer1 --> onComplete()");
            }
        };

        /**
         * 方式二：采用Subscriber抽象类
         * 说明：RxJava内置了一个实现了Observer的抽象类Subscriber
         */
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("observer2 --> onSubscribe()");
            }

            @Override
            public void onNext(String s) {
                System.out.println("observer2 --> onNext( s = " + s + ")");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("observer2 --> onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("observer2 --> onComplete()");
            }
        };

        /**
         * <p>步骤三：订阅</p>
         */
//        observable1.subscribe(observer1);

        /**
         * <p>基于事件流的调用</p>
         */
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("G");
//                emitter.onNext("H");
//                emitter.onNext("I");
//                emitter.onComplete();
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("事件流 - onSubscribe()");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("事件流 - onNext( s = " + s + " )");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("事件流 - onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("事件流 - onComplete()");
//            }
//        });

        /**
         * <p>RxJava2.0提供了多个函数式接口，用于实现简便式的观察者模式</p>
         */
        //只对onNext()事件产生响应
        Observable.just("A").subscribe(new Consumer<String>() {
            @SuppressLint("CheckResult")
            @Override
            public void accept(@SuppressLint("CheckResult") @io.reactivex.annotations.NonNull String s) throws Exception {
                System.out.println("onNext - s = " + s);
            }
        });

        //对onNext(),onError()事件产生响应
        Observable.just("B").subscribe(new Consumer<String>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
//                int i = 1/0;//测试onError()
                System.out.println("onNext - s = " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                System.out.println("onError - throwabe");
            }
        });

        //对onNext(),onError(),onComplete()事件产生响应
        Observable.just("C").subscribe(new Consumer<String>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                System.out.println("三个参数 -- onNext() s = " + s);
//                int i = 1/0;测试onError()
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                System.out.println("三个参数 -- onError()");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("三个参数 -- onComplete()");
            }
        });

        //对onNext(),onError(),onComplete(),onSubscribe()事件产生响应



    }
}
