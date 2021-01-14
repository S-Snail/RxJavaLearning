package com.zixiu.rxjavabase.ch01.operator.op03_combine_merge;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: Snail
 * Time:  2021/1/14 3:36 PM
 * FileName:  OpCombineMerge
 * 简介：组合/合并操作符
 * 分类：
 * [1]组合多个被观察者：
 * 1、按发送顺序：concat(),concatArray()
 * 2、按时间：    merge(),mergeArray()
 * 3、错误处理：concatDelayError(),mergeDelayError()
 * [2]合并多个事件：
 * 1、按数量：    zip()
 * 2、按时间：    combineLatest(),combineLatestDelayError()
 * 3、合并成一个事件发送：reduce(),collect()
 * [3]发送事件前，追加发送事件：startWith(),startWithArray()
 * [4]统计发送事件数量：count()
 */
public class OpCombineMerge {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 3.1 组合多个被观察者：concat(),concatArray()
         * 作用：
         *      组合多个被观察者一起发送数据，合并后按照发送顺序串行执行
         * 二者区别：
         *       concat()组合的被观察者数量<=4个
         *       concatArray()组合的被观察者数量可>4个
         */
        //备注：串行执行
//        Observable.concat(
//                Observable.just(1, 2, 3),
//                Observable.just(4, 5, 6),
//                Observable.just(7, 8, 9)
//        ).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("concat() -> onNext() -> value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        //串行执行
//        Observable.concatArray(
//                Observable.just(1, 2),
//                Observable.just(3, 4),
//                Observable.just(5, 6),
//                Observable.just(7, 8),
//                Observable.just(9, 10)
//        )
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("concatArray() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        /**
         * merge()/mergeArray()
         * 作用：组合多个被观察者一起发送数据，合并后按时间线并行执行
         * 二者区别：组合被观察者数量
         *          merge()组合被观察者数量<=4
         *          mergeArray()组合被观察者数量 > 4
         * 区别上述concat()操作符：同样是组合多个被观察者一起发送数据，但concat()操作符合并后是按发送顺序串行执行
         */
//        Observable.merge(
//                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
//                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS)
//        ).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                System.out.println("merge() -> onNext() -> value = " + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        /**
         * concatDelayError()/mergeDelayError()
         * 作用：
         *      当其中一个被观察者触发onError(),则会马上终止其他被观察者继续发送事件，
         *      如果想要等所有的被观察者事件发送完毕再触发onError，则使用concatDelayError()/mergeDelayError()
         */

    }

}
