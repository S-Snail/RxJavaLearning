package com.zixiu.rxjavabase.ch01.operator.op4_function;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: Snail
 * Time:  2021/1/19 4:49 PM
 * FileName:  OpFunction
 * <p>
 *     简介：功能性操作符
 *     [1]连接被观察者/观察者
 *         subscribe()
 *     [2]线程调度：
 *         2.1 subscribeOn
 *         2.2 observableOn
 *     [3]延迟操作
 *          delay()
 *     [4]在事件的生命周期中操作
 *          do()
 *     [5]错误处理（遇到错误时的机制）
 *        5.1 onErrorReturn()
 *        5.2 onErrorResumeNext()
 *        5.3 onErrorExceptionNext()
 *        5.4 retry()
 *        5.5 retryUntil()
 *        5.6 retryWhen()
 *     [6]重复发送操作
 *        6.1 repeat()
 *        6.2 repeatWhen()
 *
 *
 * </p>
 */
public class OpFunction {

    public static void main(String[] args) throws InterruptedException {
        /**
         * <p>
         *     4.1 subscribe()操作符不再赘述
         * </p>
         */

        /**
         * <p>
         *     4.2 线程调度单独成文（subscribeOn(),observableOn()）
         * </p>
         */

        /**
         * <p>
         *     4.3 delay() 使得被观察者延迟一段时间再发送事件
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        })
//                .delay(3, TimeUnit.SECONDS)//指定延时时间，时间单位
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("delay() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("delay() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("delay() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("delay() -> onComplete()");
//                    }
//                });

        /**
         * <p>
         *     4.4 do() 在事件的生命周期中操作
         *     需求场景：
         *      在事件发送&接收的整个生命周期过程中进行操作
         *      如发送事件前的初始化，发送事件后的回调请求等
         *     做用：在某个事件的生命周期中调用
         *     类型：
         *      do()的操作符有很多个，具体如下
         *          1、doOnEach():当Observable每发送一次数据事件就会调用一次（包含onNext(),onError(),onComplete()）
         *          2、Next事件
         *              2.1:执行Next事件前调用：doOnNext()
         *              2.2:执行Next事件后调用：doAfterNext()
         *          3、发送事件完毕后调用：
         *              3.1:发送错误事件时：doOnError()
         *              3.2:正常发送事件完毕后：doOnCompleted()
         *              3.3:无论正常发送完毕/异常终止 doOnterminate()
         *              3.4:最后执行：doFinally()
         *          4、订阅相关：
         *              4.1:观察者订阅时调用：doOnSubscribe()
         *              4.2:观察者取消订阅时调用：doOnUnSubscribe()
         * </p>
         */

        CountDownLatch countDownLatch = new CountDownLatch(20);
        countDownLatch.await();

    }

}
